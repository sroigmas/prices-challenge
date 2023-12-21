package com.github.sroigmas.priceschallenge.infrastructure.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.sroigmas.priceschallenge.application.port.input.FindPriceUseCase.FindPriceQuery;
import com.github.sroigmas.priceschallenge.domain.Price;
import com.github.sroigmas.priceschallenge.infrastructure.configuration.MapperConfiguration;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class PriceDbRepositoryTest {

  @InjectMocks private PriceDbRepository priceDbRepository;

  @Mock private PriceJpaRepository priceJpaRepository;
  @Spy private final ModelMapper modelMapper = new MapperConfiguration().modelMapper();

  @Test
  void givenOnePrice_whenFindingPrices_thenThePriceIsReturned() {
    Long brandId = 1L;
    Long productId = 1L;
    ZonedDateTime date = ZonedDateTime.now();

    FindPriceQuery findPriceQuery =
        FindPriceQuery.builder().brandId(brandId).productId(productId).date(date).build();

    PriceEntity priceEntity =
        PriceEntity.builder()
            .id(1L)
            .brand(BrandEntity.builder().id(brandId).name("My brand").build())
            .startDate(date)
            .endDate(date.plusDays(10))
            .rateId(1L)
            .productId(productId)
            .priority(0)
            .finalPrice(BigDecimal.valueOf(12.5))
            .ccy("EUR")
            .build();

    Mockito.when(priceJpaRepository.findPricesByIdsAndDate(brandId, productId, date))
        .thenReturn(List.of(priceEntity));

    List<Price> prices = priceDbRepository.findPricesByIdsAndDate(findPriceQuery);

    assertEquals(1, prices.size());
    assertEquals(priceEntity.getId(), prices.get(0).getId());
    assertEquals(priceEntity.getBrand().getId(), prices.get(0).getBrand().getId());
    assertEquals(priceEntity.getBrand().getName(), prices.get(0).getBrand().getName());
    assertEquals(priceEntity.getStartDate(), prices.get(0).getStartDate());
    assertEquals(priceEntity.getEndDate(), prices.get(0).getEndDate());
    assertEquals(priceEntity.getRateId(), prices.get(0).getRateId());
    assertEquals(priceEntity.getProductId(), prices.get(0).getProductId());
    assertEquals(priceEntity.getPriority(), prices.get(0).getPriority());
    assertEquals(priceEntity.getFinalPrice(), prices.get(0).getFinalPrice());
    assertEquals(priceEntity.getCcy(), prices.get(0).getCcy());
  }
}
