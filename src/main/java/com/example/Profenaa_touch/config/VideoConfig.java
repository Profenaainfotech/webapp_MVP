package com.example.Profenaa_touch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class VideoConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry r) {
        r.addResourceHandler("/videos/**")
                .addResourceLocations("file:uploads/videos/");
        r.addResourceHandler("/materials/**")
                .addResourceLocations("file:uploads/materials/");
    }
}
