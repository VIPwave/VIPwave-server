package kr.vipwave.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.dto.RestResponse;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/one-click")
@Tag(name = "OneClick Controller", description = "원클릭 링크 API")
public class OneClickRestController {
    private final OneClickService oneClickService;

    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @GetMapping("/{platformId}")
    @Operation(summary = "플랫폼별 원클릭 링크 조회", description = "플랫폼별 원클릭 링크를 조회합니다.")
    public RestResponse<OneClickResponse> getOncClick(@PathVariable @Parameter(description = "플랫폼 ID") Long platformId) {
        return RestResponse.success(oneClickService.getOneClick(platformId));
    }
}
