package kr.vipwave.server.service;

import kr.vipwave.server.dto.PlatformResponse;

import java.util.List;

public interface PlatformService {
    List<PlatformResponse> getPlatformList();
}
