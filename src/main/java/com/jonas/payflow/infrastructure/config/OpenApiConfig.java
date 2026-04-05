package com.jonas.payflow.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payflow API")
                        .version("v1")
                        .description("API REST para simulação de operações financeiras como criação de contas, transferências, depósitos, saques e consulta de saldo.")
        );
    }
}
