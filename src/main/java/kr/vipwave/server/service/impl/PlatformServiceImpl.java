package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.Platform;
import kr.vipwave.server.dto.PlatformResponse;
import kr.vipwave.server.repository.PlatformRepository;
import kr.vipwave.server.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;

    @Override
    @Transactional
    public List<PlatformResponse> getPlatformList(String chartType) {
        List<Platform> domestic = new ArrayList<>();
        List<Platform> foreign = new ArrayList<>();

        if (chartType == null) {
            return platformRepository.findAll().stream().map(PlatformResponse::fromEntity).toList();
        }

        if (chartType.equalsIgnoreCase("domestic")) {
            domestic = platformRepository.findByChartType(ChartType.DOMESTIC);
        }
        if (chartType.equalsIgnoreCase("foreign")) {
            foreign = platformRepository.findByChartType(ChartType.FOREIGN);
        }

        if (!domestic.isEmpty()) {
            return domestic.stream().map(PlatformResponse::fromEntity).toList();
        }
        if (!foreign.isEmpty()) {
            return foreign.stream().map(PlatformResponse::fromEntity).toList();
        }

        return null;
    }
}
