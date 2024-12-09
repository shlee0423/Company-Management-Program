package com.team.config;

import com.team.converter.FileConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Log4j2
public class MainConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new FileConverter());
    }

    // bean 주입하고 exchange 메소드 사용하기 위함
    @Bean
    public RestOperations restOperations(){
        return new RestTemplate();
    }
}
