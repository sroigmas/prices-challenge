package com.github.sroigmas.priceschallenge.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.domain.Brand;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPriceServiceTest {

  @InjectMocks private FindPriceService findPriceService;

  @Mock private PriceRepository priceRepository;

  @Test
  void givenIdsAndDate_whenFindingPrice_thenPriceExists() {
    FindPriceQuery findPriceQuery = FindPriceQuery.builder().build();
    Price price = buildPrice(1L, 1);
    Mockito.when(priceRepository.findPricesByIdsAndDate(findPriceQuery)).thenReturn(List.of(price));

    Optional<Price> priceOpt = findPriceService.findPriceByIdsAndDate(findPriceQuery);

    assertTrue(priceOpt.isPresent());
    assertEquals(price, priceOpt.get());
  }

  @Test
  void givenNoPrices_whenFindingPrice_thenPriceDoesntExist() {
    FindPriceQuery findPriceQuery = FindPriceQuery.builder().build();
    Mockito.when(priceRepository.findPricesByIdsAndDate(findPriceQuery)).thenReturn(List.of());

    Optional<Price> priceOpt = findPriceService.findPriceByIdsAndDate(findPriceQuery);

    assertTrue(priceOpt.isEmpty());
  }

  @Test
  void givenTwoPrices_whenFindingPrice_thenReturnsPriceWithMaxPriority() {
    FindPriceQuery findPriceQuery = FindPriceQuery.builder().build();
    Price priceWithLessPriority = buildPrice(1L, 1);
    Price priceWithMorePriority = buildPrice(2L, 2);
    Mockito.when(priceRepository.findPricesByIdsAndDate(findPriceQuery))
        .thenReturn(List.of(priceWithLessPriority, priceWithMorePriority));

    Optional<Price> priceOpt = findPriceService.findPriceByIdsAndDate(findPriceQuery);

    assertTrue(priceOpt.isPresent());
    assertEquals(priceWithMorePriority, priceOpt.get());
  }

  private Price buildPrice(Long id, Integer priority) {
    return Price.builder()
        .priority(priority)
        .id(id)
        .brand(Brand.builder().id(1L).name("brand").build())
        .startDate(ZonedDateTime.now())
        .endDate(ZonedDateTime.now().plusDays(10))
        .rateId(1L)
        .productId(1L)
        .finalPrice(BigDecimal.valueOf(12.5))
        .ccy("EUR")
        .build();
  }
}
