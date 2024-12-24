package com.example.DirectNexus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(List.of("http://localhost:3031")); // İzin verilen kaynaklar
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // İzin verilen HTTP yöntemleri
            corsConfiguration.setAllowedHeaders(List.of("*")); // İzin verilen header'lar
            corsConfiguration.setAllowCredentials(true); // Kimlik bilgilerine izin ver
            return corsConfiguration;
        }));
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/service-requests/**").authenticated() //  POST isteklerinde doğrulama
                .requestMatchers(HttpMethod.DELETE, "/api/service-requests/**").authenticated() // DELETE istekleri doğrulama gerektirir
                .anyRequest().permitAll() // Diğer istekler serbest
        );

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Yeni API ile JWT yapılandırması
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

}
