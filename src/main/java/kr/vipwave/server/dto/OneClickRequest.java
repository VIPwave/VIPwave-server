package kr.vipwave.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.DeviceType;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "원클릭 링크 생성 요청 DTO")
public class OneClickRequest {
    @Schema(description = "원클릭 플랫폼 이름")
    private String platformName;
    @Schema(description = "원클릭 플랫폼 로고")
    private String platformLogo;
    @Schema(description = "플랫폼 국내/해외 차트 타입")
    private ChartType chartType;
    @Schema(description = "원클릭 링크")
    private Map<DeviceType, List<String>> links;
}
