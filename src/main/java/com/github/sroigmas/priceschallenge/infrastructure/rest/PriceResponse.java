package com.github.sroigmas.priceschallenge.infrastructure.rest;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceResponse {

  private Long productId;
  private Long brandId;
  private Long rateId;
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private String finalPrice;
}
