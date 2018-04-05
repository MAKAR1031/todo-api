package ru.makar.todoapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${cors.origin}")
    private String origin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("CORS origin: {}", origin);
        registry.addMapping("/**")
                .allowedOrigins(origin)
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
