package kr.vipwave.server.service;

import kr.vipwave.server.dto.OneClickResponse;

public interface OneClickService {
    OneClickResponse getOneClick(Long platformId);
}
