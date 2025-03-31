package kr.vipwave.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.vipwave.server.dto.PlatformResponse;
import kr.vipwave.server.dto.RestResponse;
import kr.vipwave.server.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/platform")
@Tag(name = "Platform Controller", description = "플랫폼 API")
public class PlatformRestController {
    private final PlatformService platformService;

    @GetMapping
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "스트리밍 플랫폼 조회", description = "플랫폼을 조회합니다.")
    public RestResponse<List<PlatformResponse>> getPlatformByChart() {
        return RestResponse.success(platformService.getPlatformList());
    }
}
