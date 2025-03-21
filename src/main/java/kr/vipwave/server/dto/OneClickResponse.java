package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.domain.OneClickLink;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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
    private List<OneClickLinkResponse> links;
    @JsonProperty(value = "update_at")
    @Schema(description = "원클릭 링크 마지막 업데이트 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    public static OneClickResponse fromEntity(OneClick oneClick) {
        List<OneClickLinkResponse> deviceLinks = oneClick.getLinks().stream()
                .collect(Collectors.groupingBy(
                        OneClickLink::getDeviceType,
                        Collectors.mapping(OneClickLink::getUrl, Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> new OneClickLinkResponse(entry.getKey(), entry.getValue()))
                .toList();

        return OneClickResponse.builder()
                .id(oneClick.getId())
                .name(oneClick.getPlatform().getName())
                .chartType(oneClick.getPlatform().getChartType())
                .logo(oneClick.getPlatform().getLogo())
                .links(deviceLinks)
                .updatedAt(oneClick.getUpdatedAt())
                .build();
    }
}