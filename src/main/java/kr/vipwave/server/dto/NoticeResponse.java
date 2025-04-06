package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.Notice;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공지사항 응답 DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticeResponse {
    @Schema(description = "공지사항 ID")
    private long id;
    @Schema(description = "공지사항 제목")
    private String title;
    @Schema(description = "공지사항 내용")
    private String content;
    @Schema(description = "공지사항 링크")
    private String url;
    @Schema(description = "공지사항 순서")
    private int order;
    @Schema(description = "공지사항 작성 스태프")
    private String staffNo;
    @Schema(description = "공지사항 작성 시간")
    private LocalDateTime updatedAt;

    public static NoticeResponse fromEntity(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .url(notice.getUrl())
                .order(notice.getNoticeOrder())
                .staffNo(notice.getStaffNo())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}
