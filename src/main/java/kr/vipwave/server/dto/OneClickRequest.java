package kr.vipwave.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "원클릭 수정 DTO")
public class OneClickRequest {
    @Schema(description = "수정한 스태프 번호")
    private String staffNo;
    @Schema(description = "수정할 디바이스/링크")
    private List<OneClickLinkRequest> updatedList;
}
