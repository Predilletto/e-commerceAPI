package com.prettyletto.ecommerceapi;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ECommerceApiConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
