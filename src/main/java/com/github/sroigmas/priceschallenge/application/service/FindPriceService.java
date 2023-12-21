package com.github.sroigmas.priceschallenge.application.service;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindPriceService implements FindPriceUseCase {

  @Override
  public Optional<Price> findPriceByIdsAndDate(FindPriceQuery findPriceQuery) {
    return Optional.empty(); // TODO implement
  }
}
