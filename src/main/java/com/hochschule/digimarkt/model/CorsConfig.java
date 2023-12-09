package com.hochschule.digimarkt.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedOrigins("http://13.51.149.52")
                .allowedOrigins("http://digimarkt.online")
                .allowedOrigins("https://digimarkt.online")
                .allowedOrigins("http://digimarkt.shop")
                .allowedOrigins("https://digimarkt.shop")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
