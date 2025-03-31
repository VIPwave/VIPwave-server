package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.domain.OneClickLink;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "원클릭 링크 생성 응답 DTO")
@JsonPropertyOrder({"id", "platform", "logo", "links", "staff_no", "updated_at"})
public class OneClickResponse {
    @Schema(description = "플랫폼 ID")
    private Long id;
    @JsonProperty(value = "platform")
    @Schema(description = "플랫폼 이름")
    private String name;
    @Schema(description = "플랫폼 로고")
    private String logo;
    @Schema(description = "원클릭 링크 리스트")
    private List<OneClickLinkResponse> links;
    @JsonProperty(value = "staff_no")
    @Schema(description = "업데이트 스태프 번호")
    private String staffNo;
    @JsonProperty(value = "update_at")
    @Schema(description = "원클릭 링크 마지막 업데이트 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    public static OneClickResponse fromEntity(OneClick oneClick) {
        List<OneClickLinkResponse> deviceLinks = oneClick.getLinks().stream()
                .sorted(Comparator.comparingInt(OneClickLink::getLinkOrder))
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
                .logo(oneClick.getPlatform().getLogo())
                .links(deviceLinks)
                .staffNo(oneClick.getStaffNo())
                .updatedAt(oneClick.getUpdatedAt())
                .build();
    }
}