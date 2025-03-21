package kr.vipwave.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.vipwave.server.dto.OneClickLinkRequest;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.dto.RestResponse;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public RestResponse<OneClickResponse> getOneClick(@PathVariable @Parameter(description = "플랫폼 ID") Long platformId) {
        return RestResponse.success(oneClickService.getOneClick(platformId));
    }

    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @PatchMapping("/{id}")
    @Operation(summary = "플랫폼별 원클릭 링크 수정", description = "플랫폼별 원클릭 링크를 수정합니다.")
    public RestResponse<OneClickResponse> updateOneClick(
            @PathVariable @Parameter(description = "원클릭 링크 ID") Long id,
            @RequestBody @Parameter(description = "원클릭 링크 수정 리스트") List<OneClickLinkRequest> oneClickLinkRequest) {
        oneClickService.updateOneClick(id, oneClickLinkRequest);
        return RestResponse.success(oneClickService.getOneClick(id));
    }
}
