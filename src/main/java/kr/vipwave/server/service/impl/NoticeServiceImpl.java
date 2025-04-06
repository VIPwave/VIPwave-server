package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.Notice;
import kr.vipwave.server.dto.NoticeRequest;
import kr.vipwave.server.dto.NoticeResponse;
import kr.vipwave.server.dto.NoticeTitleResponse;
import kr.vipwave.server.repository.NoticeRepository;
import kr.vipwave.server.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeTitleResponse> getNoticeList() {
        return noticeRepository.findAllByOrderByNoticeOrderDesc()
                .stream()
                .map(NoticeTitleResponse::fromEntity)
                .toList();
    }

    @Override
    public NoticeResponse getNoticeById(Long id) {
        return NoticeResponse.fromEntity(noticeRepository.findById(id).orElse(null));
    }

    @Override
    public NoticeResponse createNotice(NoticeRequest noticeRequest) {
        return NoticeResponse.fromEntity(
                noticeRepository.save(Notice.builder()
                        .title(noticeRequest.getTitle())
                        .url(noticeRequest.getUrl())
                        .content(noticeRequest.getContent())
                        .noticeOrder(noticeRepository.findMaxNoticeOrder() + 1)
                        .staffNo(noticeRequest.getStaffNo())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
                )
        );
    }
}
