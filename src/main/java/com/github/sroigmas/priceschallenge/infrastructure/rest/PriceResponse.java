package com.github.sroigmas.priceschallenge.infrastructure.rest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PriceResponse {

  private Long productId;
  private Long brandId;
  private Long rateId;
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private String finalPrice;
}
