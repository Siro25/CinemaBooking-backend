package com.sidocinemas.cinema_booking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Expose the assets directory
        Path assetUploadDir = Paths.get("assets");
        String assetUploadPath = assetUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/assets/**")
                .addResourceLocations("file:/" + assetUploadPath + "/");
    }
}
