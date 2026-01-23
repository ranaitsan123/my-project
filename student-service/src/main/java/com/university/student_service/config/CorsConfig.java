package com.university.student_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // On autorise le partage des cookies/sessions si besoin
        config.setAllowCredentials(true); 
        
        // MODIFICATION ICI : On ajoute "null" pour tes fichiers locaux
        // Et on laisse "http://localhost:8080" pour tes anciens tests
        config.setAllowedOriginPatterns(List.of(
            "http://localhost:8080", 
            "http://localhost:5500", // Pour Live Server VS Code
            "http://127.0.0.1:5500",
            "null"                   // INDISPENSABLE pour l'erreur que tu as eue
        ));

        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}