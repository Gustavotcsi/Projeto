package com.ProjetoExtensao.Projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilitei proteção CSRF só para facilitar testes no Postman
                    .authorizeHttpRequests(auth -> auth
                            // libera acesso sem autenticação para swagger e documentação
                            .requestMatchers(
                                    "/v3/api-docs/**",
                                    "/swagger-ui.html",
                                    "/swagger-ui/**",
                                    "/swagger-ui/index.html",
                                    "/favicon.ico"
                            ).permitAll()
                            // exige autenticação para todo o resto
                            .anyRequest().authenticated()
                    )
                    .httpBasic(withDefaults()); // Ativa autenticação HTTP Basic
            return http.build();
        }
}
