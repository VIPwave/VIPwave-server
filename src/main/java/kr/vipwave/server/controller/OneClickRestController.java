package kr.vipwave.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.vipwave.server.dto.OneClickRequest;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.dto.RestResponse;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/one-click")
@Tag(name = "OneClick Controller", description = "원클릭 링크 API")
public class OneClickRestController {
    private final OneClickService oneClickService;

    @PostMapping
    @Operation(summary = "원클릭 링크 생성", description = "원클릭 링크를 생성합니다.")
    @ApiResponse(
            responseCode = "201",
            description = "생성됨"
    )
    public RestResponse<OneClickResponse> createOneClick(
            @RequestBody @Schema(implementation = OneClickRequest.class) OneClickRequest oneClickRequest) {
        return RestResponse.created(oneClickService.createOneClick(oneClickRequest));
    }

    @Operation(summary = "원클릭 링크 국내/해외별 조회", description = "원클릭 링크를 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @GetMapping
    public RestResponse<Map<String, List<OneClickResponse>>> getOncClickByChart(
            @RequestParam(required = false) @Parameter(description = "국내/해외 타입") String chartType) {
        return RestResponse.success(oneClickService.getOneClickList(chartType));
    }

    @Operation(summary = "플랫폼별 원클릭 링크 조회", description = "플랫폼별 원클릭 링크를 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @GetMapping("/{platformId}")
    public RestResponse<OneClickResponse> getOncClick(@PathVariable @Parameter(description = "플랫폼 ID") Long platformId) {
        return RestResponse.success(oneClickService.getOneClick(platformId));
    }
}
