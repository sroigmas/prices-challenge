package com.github.sroigmas.priceschallenge.application.service;

import com.github.sroigmas.priceschallenge.application.exception.ApplicationNotFoundException;
import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase;
import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPriceService implements FindPriceUseCase {

  private PriceRepository priceRepository;

  @Override
  public Price findPriceByIdsAndDate(FindPriceQuery findPriceQuery) {
    List<Price> prices = priceRepository.findPricesByIdsAndDate(findPriceQuery);

    return prices.stream()
        .max(Comparator.comparing(Price::getPriority))
        .orElseThrow(
            () ->
                new ApplicationNotFoundException(
                    String.format(
                        "Price for brandId=%d, productId=%d and date=%s could not be found.",
                        findPriceQuery.getBrandId(),
                        findPriceQuery.getProductId(),
                        findPriceQuery.getDate())));
  }
}
