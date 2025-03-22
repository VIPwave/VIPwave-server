package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.DeviceType;
import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.domain.OneClickLink;
import kr.vipwave.server.dto.OneClickLinkRequest;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.repository.OneClickLinkRepository;
import kr.vipwave.server.repository.OneClickRepository;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OneClickServiceImpl implements OneClickService {
    private final OneClickRepository oneClickRepository;
    private final OneClickLinkRepository oneClickLinkRepository;

    @Override
    @Transactional
    public OneClickResponse getOneClick(Long platformId) {
        OneClick oneClick = oneClickRepository.findByPlatformId(platformId);
        return OneClickResponse.fromEntity(oneClick);
    }

    @Override
    @Transactional
    public List<OneClickResponse> getOneClickList() {
        return oneClickRepository.findAll()
                .stream()
                .map(OneClickResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public void updateOneClick(Long id, List<OneClickLinkRequest> oneClickLinkRequest) {
        OneClick oneClick = oneClickRepository.findById(id).orElseThrow();
        Map<DeviceType, List<OneClickLink>> existingMap = oneClickLinkRepository.findByOneClick(oneClick).stream()
                .collect(Collectors.groupingBy(OneClickLink::getDeviceType));

        for (OneClickLinkRequest update : oneClickLinkRequest) {
            DeviceType type = update.getDeviceType();
            List<String> incomingUrls = update.getLinks();

            if (incomingUrls == null || incomingUrls.isEmpty()) {
                oneClickLinkRepository.deleteByOneClickAndDeviceType(oneClick, type);
            } else {
                List<String> existingUrls = existingMap.getOrDefault(type, List.of())
                        .stream().map(OneClickLink::getUrl).toList();

                if (!existingUrls.equals(incomingUrls)) {
                    oneClickLinkRepository.deleteByOneClickAndDeviceType(oneClick, type);
                    List<OneClickLink> newLinks = IntStream.range(1, incomingUrls.size() + 1)
                            .mapToObj(i -> OneClickLink.builder()
                                    .deviceType(type)
                                    .url(incomingUrls.get(i - 1))
                                    .linkOrder(i)
                                    .oneClick(oneClick)
                                    .build())
                            .toList();
                    oneClickLinkRepository.saveAll(newLinks);
                }
            }
        }

        oneClick.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        oneClickRepository.save(oneClick);
    }
}
