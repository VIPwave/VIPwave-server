package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.repository.OneClickRepository;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OneClickServiceImpl implements OneClickService {
    private final OneClickRepository oneClickRepository;

    @Override
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
}
