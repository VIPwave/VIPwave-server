package kr.vipwave.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.Notice;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeTitleResponse {
    @Schema(description = "공지사항 ID")
    private long id;
    @Schema(description = "공지사항 제목")
    private String title;
    @Schema(description = "공지사항 작성 시간")
    private LocalDateTime updatedAt;

    public static NoticeTitleResponse fromEntity(Notice notice) {
        return NoticeTitleResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}
