package kr.vipwave.server.service.impl;

import kr.vipwave.server.dto.PlatformResponse;
import kr.vipwave.server.repository.PlatformRepository;
import kr.vipwave.server.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {
    private final PlatformRepository platformRepository;

    @Override
    @Transactional
    public List<PlatformResponse> getPlatformList() {
        return platformRepository.findAll().stream().map(PlatformResponse::fromEntity).toList();
    }
}
