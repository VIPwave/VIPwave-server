package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.repository.OneClickRepository;
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
    private final OneClickRepository oneClickRepository;

    @Override
    @Transactional
    public OneClickResponse getOneClick(Long platformId) {
        OneClick oneClick = oneClickRepository.findByPlatformId(platformId);
        return OneClickResponse.fromEntityWithLinks(oneClick);
    }

    @Override
    @Transactional
    public Map<String, List<OneClickResponse>> getOneClickList(String chartType) {
        List<OneClick> domestic = new ArrayList<>();
        List<OneClick> foreign = new ArrayList<>();

        Map<String, List<OneClickResponse>> response = new HashMap<>();
        if (chartType == null) {
            response.put("one-click", oneClickRepository.findAll().stream().map(OneClickResponse::fromEntity).toList());
            return response;
        }

        if (chartType.equalsIgnoreCase("domestic")) {
            domestic = oneClickRepository.findByChartType(ChartType.DOMESTIC);
        }
        if (chartType.equalsIgnoreCase("foreign")) {
            foreign = oneClickRepository.findByChartType(ChartType.FOREIGN);
        }

        if (!domestic.isEmpty()) {
            response.put("domestic", domestic.stream().map(OneClickResponse::fromEntity).toList());
        }
        if (!foreign.isEmpty()) {
            response.put("foreign", foreign.stream().map(OneClickResponse::fromEntity).toList());
        }

        return response;
    }
}
