package kr.vipwave.server.service;

import kr.vipwave.server.dto.OneClickLinkRequest;
import kr.vipwave.server.dto.OneClickResponse;

import java.util.List;

public interface OneClickService {
    OneClickResponse getOneClick(Long platformId);

    List<OneClickResponse> getOneClickList();

    void updateOneClick(Long id, List<OneClickLinkRequest> oneClickLinkRequest);
}
