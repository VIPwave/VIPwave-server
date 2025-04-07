package kr.vipwave.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "공지사항 순서 변경 요청 DTO")
public class NoticeOrderUpdateRequest {
    @Schema(description = "공지사항 ID")
    private Long id;
    @Schema(description = "공지사항 순서")
    private Integer order;
}
