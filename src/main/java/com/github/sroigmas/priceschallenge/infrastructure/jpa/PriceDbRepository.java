package com.github.sroigmas.priceschallenge.infrastructure.jpa;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PriceDbRepository implements PriceRepository {

  private PriceJpaRepository priceJpaRepository;
  private ModelMapper modelMapper;

  @Override
  public List<Price> findPricesByIdsAndDate(FindPriceQuery findPriceQuery) {
    List<PriceEntity> prices =
        priceJpaRepository.findPricesByIdsAndDate(
            findPriceQuery.getBrandId(), findPriceQuery.getProductId(), findPriceQuery.getDate());

    return prices.stream().map(price -> modelMapper.map(price, Price.class)).toList();
  }
}
