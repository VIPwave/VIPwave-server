package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.*;
import kr.vipwave.server.dto.OneClickRequest;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.repository.LinkRepository;
import kr.vipwave.server.repository.OneClickRepository;
import kr.vipwave.server.repository.PlatformRepository;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OneClickServiceImpl implements OneClickService {
    private final LinkRepository linkRepository;
    private final OneClickRepository oneClickRepository;
    private final PlatformRepository platformRepository;

    @Override
    @Transactional
    public OneClickResponse getOneClick(Long platformId) {
        OneClick oneClick = oneClickRepository.findByPlatformId(platformId);
        return OneClickResponse.fromEntity(oneClick);
    }

    @Override
    public Map<String, List<OneClickResponse>> getOneClickList(String chartType) {
        List<OneClick> domestic = new ArrayList<>();
        List<OneClick> foreign = new ArrayList<>();

        if (chartType == null || chartType.equalsIgnoreCase("domestic")) {
            domestic = oneClickRepository.findByChartType(ChartType.DOMESTIC);
        }
        if (chartType == null || chartType.equalsIgnoreCase("foreign")) {
            foreign = oneClickRepository.findByChartType(ChartType.FOREIGN);
        }

        Map<String, List<OneClickResponse>> response = new HashMap<>();
        if (!domestic.isEmpty()) {
            response.put("domestic", domestic.stream().map(OneClickResponse::fromEntity).toList());
        }
        if (!foreign.isEmpty()) {
            response.put("foreign", foreign.stream().map(OneClickResponse::fromEntity).toList());
        }

        return response;
    }

    @Override
    public OneClickResponse createOneClick(OneClickRequest oneClickRequest) {
        Platform platform = platformRepository.findByName(oneClickRequest.getPlatformName())
                .orElseGet(() -> {
                    Platform newPlatform = Platform.builder()
                            .name(oneClickRequest.getPlatformName())
                            .logo(oneClickRequest.getPlatformLogo())
                            .build();
                    return platformRepository.save(newPlatform);
                });

        OneClick tempOneClick = OneClick.builder()
                .platform(platform)
                .chartType(oneClickRequest.getChartType())
                .build();

        OneClick oneClick = oneClickRepository.save(tempOneClick);

        List<Link> links = oneClickRequest.getLinks()
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(url -> Link.builder()
                                .linkType(LinkType.ONECLICK)
                                .deviceType(entry.getKey())
                                .url(url)
                                .oneClick(oneClick)
                                .build()))
                .toList();

        linkRepository.saveAll(links);
        oneClick.setLinks(links);

        return OneClickResponse.fromEntity(oneClick);
    }
}
