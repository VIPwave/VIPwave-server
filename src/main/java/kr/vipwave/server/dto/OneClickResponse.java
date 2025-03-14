package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.vipwave.server.domain.DeviceType;
import kr.vipwave.server.domain.Link;
import kr.vipwave.server.domain.OneClick;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneClickResponse {
    private Long id;
    private String name;
    private String logo;
    private Map<DeviceType, List<String>> links;

    public static OneClickResponse fromEntity(OneClick oneClick) {
        Map<DeviceType, List<String>> deviceLinks = oneClick.getLinks().stream()
                .collect(Collectors.groupingBy(
                        Link::getDeviceType,
                        Collectors.mapping(Link::getUrl, Collectors.toList())
                ));

        return OneClickResponse.builder()
                .id(oneClick.getId())
                .name(oneClick.getPlatform().getName())
                .logo(oneClick.getPlatform().getLogo())
                .links(deviceLinks)
                .build();
    }
}