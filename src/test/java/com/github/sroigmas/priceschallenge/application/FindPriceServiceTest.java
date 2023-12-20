package com.github.sroigmas.priceschallenge.application;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.sroigmas.priceschallenge.application.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPriceServiceTest {

  @InjectMocks private FindPriceService findPriceService;

  @Test
  void givenIdsAndDate_whenFindingPrice_thenPriceExists() {
    FindPriceQuery findPriceQuery = FindPriceQuery.builder().build();

    Optional<Price> price = findPriceService.findPriceByIdsAndDate(findPriceQuery);

    assertTrue(price.isPresent());
  }
}
