package com.hochschule.digimarkt.controller.model;

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
                .allowedOrigins("https://1af4-192-108-48-250.ngrok.io")
                .allowedOrigins("http://d3q9fd96onr6h9.cloudfront.net")
                .allowedOrigins("https://d3q9fd96onr6h9.cloudfront.net")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
