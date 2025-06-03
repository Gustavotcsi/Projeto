package com.ProjetoExtensao.Projeto;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Agendamentos")
                        .version("1.0")
                        .description("Documentação da API para o sistema de gestão de agendamentos de barbearia e salões.")
                        .contact(new Contact()
                                .name("Gustavo Távora")
                                .email("gustavo@email.com")
                                .url("https://github.com/gustavotavora")
                        )
                );
    }
}

