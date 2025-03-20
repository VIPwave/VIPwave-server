package kr.vipwave.server.dto;

import kr.vipwave.server.domain.Platform;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlatformResponse {
    private Long id;
    private String name;
    private String logo;

    public static PlatformResponse fromEntity(Platform platform) {
        return PlatformResponse.builder()
                .id(platform.getId())
                .name(platform.getName())
                .logo(platform.getLogo())
                .build();
    }
}
