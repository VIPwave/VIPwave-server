package kr.vipwave.server.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("VIPwave")
                .version("v0.0.1")
                .description("VIPwave REST API");

        return new OpenAPI()
                .servers(List.of(new Server().url("https://vipwave.kr")))
                .components(new Components())
                .info(info);
    }
}
