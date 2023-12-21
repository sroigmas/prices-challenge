package com.github.sroigmas.priceschallenge.application.port.output;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.List;

public interface PriceRepository {

  List<Price> findPricesByIdsAndDate(FindPriceQuery findPriceQuery);
}
