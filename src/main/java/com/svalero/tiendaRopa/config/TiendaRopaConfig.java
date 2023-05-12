package com.svalero.tiendaRopa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TiendaRopaConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
