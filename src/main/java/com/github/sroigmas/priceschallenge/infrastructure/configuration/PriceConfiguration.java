package com.github.sroigmas.priceschallenge.infrastructure.configuration;

import com.github.sroigmas.priceschallenge.application.FindPriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfiguration {

  @Bean
  public FindPriceService findPriceService() {
    return new FindPriceService();
  }
}
