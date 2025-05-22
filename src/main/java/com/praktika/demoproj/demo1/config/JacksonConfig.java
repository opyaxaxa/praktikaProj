package com.praktika.demoproj.demo1.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .modulesToInstall(new JtsModule());
    }
}