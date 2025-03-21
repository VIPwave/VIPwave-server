package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "원클릭 링크 응답 DTO")
public class OneClickLinkResponse {
    @Schema(description = "기기 타입")
    @JsonProperty(value = "device_type")
    private DeviceType deviceType;
    @Schema(description = "원클릭 링크 리스트")
    private List<String> links;
}
