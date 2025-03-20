package kr.vipwave.server.service;

import kr.vipwave.server.dto.OneClickResponse;

import java.util.List;
import java.util.Map;

public interface OneClickService {
    OneClickResponse getOneClick(Long platformId);

    Map<String, List<OneClickResponse>> getOneClickList(String chartType);
}
