package kr.vipwave.server.dto;

import kr.vipwave.server.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkResponse {
    private Long id;
    private String linkType;
    private String link;
    private String url;
    private String deviceType;

    public static LinkResponse fromEntity(Link link) {
        return LinkResponse.builder()
                .id(link.getId())
                .linkType(link.getLinkType().toString())
                .url(link.getUrl())
                .deviceType(link.getDeviceType().toString())
                .build();
    }
}
