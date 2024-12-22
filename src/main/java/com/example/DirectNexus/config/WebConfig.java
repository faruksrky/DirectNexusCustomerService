package com.example.DirectNexus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableMethodSecurity
@Configuration
public class WebConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CORS Konfigürasyonu
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(List.of("http://localhost:3031")); // İzin verilen kaynaklar
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // İzin verilen HTTP yöntemleri
            corsConfiguration.setAllowedHeaders(List.of("*")); // İzin verilen header'lar
            corsConfiguration.setAllowCredentials(true); // Kimlik bilgilerine izin ver
            return corsConfiguration;
        }));

        // CSRF Devre Dışı
        http.csrf(csrf -> csrf.disable());

        // Yetkilendirme ve Oturum Yönetimi
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/service-requests/**").authenticated() // Sadece POST isteklerinde JWT doğrulama
                .anyRequest().permitAll() // Diğer tüm istekler serbest
        );

        // Stateless Oturum Yönetimi
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // OAuth2 Resource Server JWT yapılandırması
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> {
            jwtConfigurer.decoder(customJwtDecoder()); // Özel JWT Decoder kullan
        }));

        return http.build();
    }

    // Özel JWT Decoder Bean
    @Bean
    public JwtDecoder customJwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri("http://localhost:9082/realms/DirectNexus/protocol/openid-connect/certs").build();

        // Token doğrulama özelleştirmesi
        jwtDecoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer("http://localhost:9082/realms/DirectNexus")
        );

        // Zaman damgası doğrulayıcı ekleme
        jwtDecoder.setJwtValidator(new JwtTimestampValidator());

        return jwtDecoder;
    }
}
