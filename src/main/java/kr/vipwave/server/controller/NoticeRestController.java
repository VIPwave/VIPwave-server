package kr.vipwave.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.vipwave.server.dto.*;
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
        return RestResponse.success(noticeService.getNoticeList(), "공지사항이 성공적으로 조회되었습니다.");
    }

    @GetMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 단일 조회", description = "단일 공지사항을 조회합니다.")
    public RestResponse<NoticeResponse> getNotice(@PathVariable Long id) {
        return RestResponse.success(noticeService.getNoticeById(id), "공지사항이 정상적으로 조회되었습니다.");
    }

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "생성 완료"
    )
    @Operation(summary = "공지사항 생성", description = "공지사항을 추가합니다.")
    public RestResponse<NoticeResponse> createNotice(@RequestBody NoticeRequest noticeRequest) {
        return RestResponse.created(noticeService.createNotice(noticeRequest), "공지사항이 정상적으로 생성되었습니다.");
    }

    @PutMapping("/order")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 순서 변경", description = "공지사항의 순서를 변경합니다.")
    public RestResponse<List<NoticeResponse>> updateNoticeOrder(@RequestBody List<NoticeOrderUpdateRequest> noticeOrderUpdateRequests) {
        return RestResponse.success(noticeService.updateNoticeOrder(noticeOrderUpdateRequests), "공지사항 순서가 정상적으로 업데이트되었습니다.");
    }

    @PutMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 수정", description = "공지사항을 수정합니다.")
    public RestResponse<NoticeResponse> updateNotice(@PathVariable Long id, @RequestBody NoticeRequest noticeRequest) {
        return RestResponse.success(noticeService.updateNotice(id, noticeRequest), "공지사항이 정상적으로 업데이트되었습니다.");
    }

    @DeleteMapping("/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "성공"
    )
    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    public RestResponse<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return RestResponse.success(null, "공지사항이 정상적으로 삭제되었습니다.");
    }
}
