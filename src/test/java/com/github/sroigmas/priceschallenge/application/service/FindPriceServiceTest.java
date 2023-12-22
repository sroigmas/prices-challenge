package com.github.sroigmas.priceschallenge.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.sroigmas.priceschallenge.application.exception.ApplicationNotFoundException;
import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.application.port.output.PriceRepository;
import com.github.sroigmas.priceschallenge.domain.Brand;
import com.github.sroigmas.priceschallenge.domain.Price;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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
    Price expectedPrice = buildPrice(1L, 1);
    Mockito.when(priceRepository.findPricesByIdsAndDate(findPriceQuery))
        .thenReturn(List.of(expectedPrice));

    Price actualPrice = findPriceService.findPriceByIdsAndDate(findPriceQuery);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  void givenNoPrices_whenFindingPrice_thenPriceDoesntExist() {
    ZonedDateTime date = ZonedDateTime.of(2023, 12, 22, 12, 0, 0, 0, ZoneOffset.UTC);
    FindPriceQuery findPriceQuery =
        FindPriceQuery.builder().brandId(1L).productId(35455L).date(date).build();
    Mockito.when(priceRepository.findPricesByIdsAndDate(findPriceQuery)).thenReturn(List.of());

    ApplicationNotFoundException exception =
        Assertions.assertThrows(
            ApplicationNotFoundException.class,
            () -> findPriceService.findPriceByIdsAndDate(findPriceQuery));

    assertEquals(
        "Price for brandId=1, productId=35455 and date=2023-12-22T12:00Z could not be found.",
        exception.getMessage());
  }

  @Test
  void givenTwoPrices_whenFindingPrice_thenReturnsPriceWithMaxPriority() {
    FindPriceQuery findPriceQuery = FindPriceQuery.builder().build();
    Price priceWithLessPriority = buildPrice(1L, 1);
    Price priceWithMorePriority = buildPrice(2L, 2);
    Mockito.when(priceRepository.findPricesByIdsAndDate(findPriceQuery))
        .thenReturn(List.of(priceWithLessPriority, priceWithMorePriority));

    Price actualPrice = findPriceService.findPriceByIdsAndDate(findPriceQuery);

    assertEquals(priceWithMorePriority, actualPrice);
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
