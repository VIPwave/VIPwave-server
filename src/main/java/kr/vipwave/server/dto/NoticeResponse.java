package kr.vipwave.server.dto;

import kr.vipwave.server.domain.Notice;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {
    private long id;
    private String title;
    private String content;
    private String staffNo;
    private LocalDateTime updatedAt;

    public static NoticeResponse fromEntity(Notice notice) {
        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .staffNo(notice.getStaffNo())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}
