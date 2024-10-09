package br.com.projeto.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.projeto.crud.utils.LoggerUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Documentation", version = "v1"), security = {
		@SecurityRequirement(name = "basicAuth") })
@io.swagger.v3.oas.annotations.security.SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		LoggerUtils log = LoggerUtils.createLoggerSize30(OpenAPI.class);

		log.infoLoadingBean();
		OpenAPI openApi = new OpenAPI()
				.components(new Components().addSecuritySchemes("basicAuth",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("basicAuth"));
		log.infoCreateBean();
		return openApi;
	}

}
