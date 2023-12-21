package com.github.sroigmas.priceschallenge.infrastructure.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class PriceControllerIT {

  @Autowired private MockMvc mvc;

  @ParameterizedTest
  @MethodSource
  void givenIdsAndDate_whenGettingPrice_thenReturnsPrice(
      ZonedDateTime date, Long productId, Long brandId, PriceResponse expectedResponse)
      throws Exception {
    ResultActions result =
        mvc.perform(
                get("/api/v1/prices")
                    .param("date", date.toString())
                    .param("product_id", productId.toString())
                    .param("brand_id", brandId.toString()))
            .andDo(print());

    result
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.product_id").value(expectedResponse.getProductId()))
        .andExpect(jsonPath("$.brand_id").value(expectedResponse.getBrandId()))
        .andExpect(jsonPath("$.rate_id").value(expectedResponse.getRateId()))
        .andExpect(
            jsonPath("$.start_date")
                .value(expectedResponse.getStartDate().format(DateTimeFormatter.ISO_INSTANT)))
        .andExpect(
            jsonPath("$.end_date")
                .value(expectedResponse.getEndDate().format(DateTimeFormatter.ISO_INSTANT)))
        .andExpect(jsonPath("$.final_price").value(expectedResponse.getFinalPrice()));
  }

  private static Stream<Arguments> givenIdsAndDate_whenGettingPrice_thenReturnsPrice() {
    Arguments args1 =
        Arguments.of(
            ZonedDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.UTC),
            35455L,
            1L,
            PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .rateId(1L)
                .startDate(ZonedDateTime.of(2020, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC))
                .endDate(ZonedDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC))
                .finalPrice("35.50")
                .build());

    Arguments args2 =
        Arguments.of(
            ZonedDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneOffset.UTC),
            35455L,
            1L,
            PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .rateId(2L)
                .startDate(ZonedDateTime.of(2020, 6, 14, 15, 0, 0, 0, ZoneOffset.UTC))
                .endDate(ZonedDateTime.of(2020, 6, 14, 18, 30, 0, 0, ZoneOffset.UTC))
                .finalPrice("25.45")
                .build());

    Arguments args3 =
        Arguments.of(
            ZonedDateTime.of(2020, 6, 14, 21, 0, 0, 0, ZoneOffset.UTC),
            35455L,
            1L,
            PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .rateId(1L)
                .startDate(ZonedDateTime.of(2020, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC))
                .endDate(ZonedDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC))
                .finalPrice("35.50")
                .build());

    Arguments args4 =
        Arguments.of(
            ZonedDateTime.of(2020, 6, 15, 10, 0, 0, 0, ZoneOffset.UTC),
            35455L,
            1L,
            PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .rateId(3L)
                .startDate(ZonedDateTime.of(2020, 6, 15, 0, 0, 0, 0, ZoneOffset.UTC))
                .endDate(ZonedDateTime.of(2020, 6, 15, 11, 0, 0, 0, ZoneOffset.UTC))
                .finalPrice("30.50")
                .build());

    Arguments args5 =
        Arguments.of(
            ZonedDateTime.of(2020, 6, 16, 21, 0, 0, 0, ZoneOffset.UTC),
            35455L,
            1L,
            PriceResponse.builder()
                .productId(35455L)
                .brandId(1L)
                .rateId(4L)
                .startDate(ZonedDateTime.of(2020, 6, 15, 16, 0, 0, 0, ZoneOffset.UTC))
                .endDate(ZonedDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC))
                .finalPrice("38.95")
                .build());

    return Stream.of(args1, args2, args3, args4, args5);
  }
}
