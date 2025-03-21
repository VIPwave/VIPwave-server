package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.DeviceType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "원클릭 링크 수정 DTO")
public class OneClickLinkRequest {
    @Schema(description = "기기 타입")
    @JsonProperty(value = "device_type")
    private DeviceType deviceType;
    @Schema(description = "원클릭 링크")
    private List<String> links;
}
