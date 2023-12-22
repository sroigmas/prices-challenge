package com.github.sroigmas.priceschallenge.application.port.input;

import com.github.sroigmas.priceschallenge.domain.Price;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Value;

public interface FindPriceUseCase {

  Price findPriceByIdsAndDate(FindPriceQuery findPriceQuery);

  @Value
  @Builder
  class FindPriceQuery {
    ZonedDateTime date;
    Long productId;
    Long brandId;
  }
}
