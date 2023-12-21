package com.github.sroigmas.priceschallenge.infrastructure.jpa;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PriceDbRepository implements PriceRepository {

  @Override
  public List<Price> findPricesByIdsAndDate(FindPriceQuery findPriceQuery) {
    return List.of(); // TODO implement
  }
}
