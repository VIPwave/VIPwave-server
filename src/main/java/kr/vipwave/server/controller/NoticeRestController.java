package kr.vipwave.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.vipwave.server.dto.NoticeRequest;
import kr.vipwave.server.dto.NoticeResponse;
import kr.vipwave.server.dto.NoticeTitleResponse;
import kr.vipwave.server.dto.RestResponse;
import kr.vipwave.server.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
@Tag(name = "Notice Controller", description = "공지사항 API")
public class NoticeRestController {
    private final NoticeService noticeService;

    @GetMapping
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 전체 조회", description = "전체 공지사항을 조회합니다.")
    public RestResponse<List<NoticeTitleResponse>> getNoticeList() {
        return RestResponse.success(noticeService.getNoticeList());
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 단일 조회", description = "단일 공지사항을 조회합니다.")
    public RestResponse<NoticeResponse> getNotice(@PathVariable Long id) {
        return RestResponse.success(noticeService.getNoticeById(id));
    }

    @PostMapping
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 생성", description = "공지사항을 추가합니다.")
    public RestResponse<NoticeResponse> createNotice(@RequestBody NoticeRequest noticeRequest) {
        return RestResponse.success(noticeService.createNotice(noticeRequest));
    }

    @PutMapping("/{id}")
    public RestResponse<Void> updateNotice(@PathVariable String id) {
        return new RestResponse<>();
    }
}
