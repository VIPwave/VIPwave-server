package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.DeviceType;
import kr.vipwave.server.domain.Link;
import kr.vipwave.server.domain.OneClick;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "원클릭 링크 생성 응답 DTO")
public class OneClickResponse {
    @Schema(description = "원클릭 링크 ID")
    private Long id;
    @Schema(description = "원클릭 링크 플랫폼 이름")
    private String name;
    @JsonProperty(value = "chart_type")
    @Schema(description = "원클릭 플랫폼 타입")
    private ChartType chartType;
    @Schema(description = "원클릭 링크 플랫폼 로고")
    private String logo;
    @Schema(description = "원클릭 링크 리스트")
    private Map<DeviceType, List<String>> links;
    @JsonProperty(value = "update_at")
    @Schema(description = "원클릭 링크 마지막 업데이트 시간")
    private LocalDateTime updatedAt;

    public static OneClickResponse fromEntityWithLinks(OneClick oneClick) {
        Map<DeviceType, List<String>> deviceLinks = oneClick.getLinks().stream()
                .collect(Collectors.groupingBy(
                        Link::getDeviceType,
                        Collectors.mapping(Link::getUrl, Collectors.toList())
                ));

        return OneClickResponse.builder()
                .id(oneClick.getId())
                .name(oneClick.getPlatform().getName())
                .chartType(oneClick.getChartType())
                .logo(oneClick.getPlatform().getLogo())
                .links(deviceLinks)
                .updatedAt(oneClick.getUpdatedAt())
                .build();
    }

    public static OneClickResponse fromEntity(OneClick oneClick) {
        return OneClickResponse.builder()
                .id(oneClick.getId())
                .name(oneClick.getPlatform().getName())
                .chartType(oneClick.getChartType())
                .logo(oneClick.getPlatform().getLogo())
                .build();
    }
}