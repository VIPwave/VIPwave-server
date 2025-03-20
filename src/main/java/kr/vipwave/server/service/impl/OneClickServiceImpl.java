package kr.vipwave.server.service.impl;

import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.repository.OneClickRepository;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OneClickServiceImpl implements OneClickService {
    private final OneClickRepository oneClickRepository;

    @Override
    @Transactional
    public OneClickResponse getOneClick(Long platformId) {
        OneClick oneClick = oneClickRepository.findByPlatformId(platformId);
        return OneClickResponse.fromEntity(oneClick);
    }


}
