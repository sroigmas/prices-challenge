package com.github.sroigmas.priceschallenge.infrastructure.rest;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase;
import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
@AllArgsConstructor
public class PriceController {

  private FindPriceUseCase findPriceUseCase;
  private ModelMapper modelMapper;

  @GetMapping
  public PriceResponse getPriceByIdsAndDate(
      @RequestParam ZonedDateTime date,
      @RequestParam("product_id") Long productId,
      @RequestParam("brand_id") Long brandId) {
    Price price =
        findPriceUseCase.findPriceByIdsAndDate(
            FindPriceQuery.builder().date(date).productId(productId).brandId(brandId).build());

    return modelMapper.map(price, PriceResponse.class);
  }
}
