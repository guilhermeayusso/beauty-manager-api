package br.com.fiap.beautymanagerapi.configuracao;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI (){
        return  new OpenAPI()
                .info(
                new Info().title("Beauty Manager API")
                        .description("API de agendamento e gerenciamento para serviços de beleza e bem-estar")
                        .version("V1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact().name("Guilherme Ayusso").email("guilherme.ayusso@gmail.com"))
        );
    }


}
