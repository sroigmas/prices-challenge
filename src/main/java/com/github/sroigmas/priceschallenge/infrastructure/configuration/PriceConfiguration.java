package com.github.sroigmas.priceschallenge.infrastructure.configuration;

import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.application.service.FindPriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfiguration {

  @Bean
  public FindPriceService findPriceService(PriceRepository priceRepository) {
    return new FindPriceService(priceRepository);
  }
}
