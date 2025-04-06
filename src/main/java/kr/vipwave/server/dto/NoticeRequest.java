package kr.vipwave.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "공지사항 생성 요청 DTO")
public class NoticeRequest {
    @Schema(description = "공지사항 제목")
    private String title;
    @Schema(description = "공지사항 내용")
    private String content;
    @Schema(description = "공지사항 링크")
    private String url;
    @Schema(description = "공지사항 작성 스태프")
    private String staffNo;
}
