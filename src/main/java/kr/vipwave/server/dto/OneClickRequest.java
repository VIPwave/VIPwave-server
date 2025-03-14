package kr.vipwave.server.dto;

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
public class OneClickRequest {
    private String platformName;
    private String platformLogo;
    private ChartType chartType;
    private Map<DeviceType, List<String>> links;
}
