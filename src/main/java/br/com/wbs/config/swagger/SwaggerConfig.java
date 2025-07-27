package br.com.wbs.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Game Catalog API")
                .description("API for managing game studios and games")
                .version("1.0")
                .contact(new Contact()
                        .name("William Andrade")
                        .url("https://github.com/wbsfs")
                        .email("williamandrade1058@gmail.com")));
    }
}
