package com.example.demojwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //Creamos un componente de Spring para que se pueda inyectar en otras clases y configuramos la seguridad de la aplicación.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Desactivamos el cross site request forgery para poder hacer peticiones desde el front end que esta en otro dominio diferente.
        return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authRequest ->
                        //todos los endpoints que empiecen con /auth/ son publicos y cualquier otro necesita autenticacion.
                        authRequest.requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(withDefaults()).build();
    }
}
