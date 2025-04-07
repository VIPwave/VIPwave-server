package kr.vipwave.server.service;

import kr.vipwave.server.dto.NoticeOrderUpdateRequest;
import kr.vipwave.server.dto.NoticeRequest;
import kr.vipwave.server.dto.NoticeResponse;
import kr.vipwave.server.dto.NoticeTitleResponse;

import java.util.List;

public interface NoticeService {
    List<NoticeTitleResponse> getNoticeList();

    NoticeResponse getNoticeById(Long id);

    NoticeResponse createNotice(NoticeRequest noticeRequest);

    List<NoticeResponse> updateNoticeOrder(List<NoticeOrderUpdateRequest> noticeOrderUpdateRequests);

    NoticeResponse updateNotice(Long id, NoticeRequest noticeRequest);

    void deleteNotice(Long id);
}
