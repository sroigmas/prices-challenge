package com.github.sroigmas.priceschallenge.application.service;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase;
import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPriceService implements FindPriceUseCase {

  private PriceRepository priceRepository;

  @Override
  public Optional<Price> findPriceByIdsAndDate(FindPriceQuery findPriceQuery) {
    List<Price> prices = priceRepository.findPricesByIdsAndDate(findPriceQuery);

    return prices.stream().max(Comparator.comparing(Price::getPriority));
  }
}
