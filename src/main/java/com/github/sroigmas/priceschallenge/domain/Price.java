package com.github.sroigmas.priceschallenge.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {
  private Long id;
  private Brand brand;
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private Long rateId;
  private Long productId;
  private Integer priority;
  private BigDecimal finalPrice;
  private String ccy;
}
