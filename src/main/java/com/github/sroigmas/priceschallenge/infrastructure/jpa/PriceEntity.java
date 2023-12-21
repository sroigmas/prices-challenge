package com.github.sroigmas.priceschallenge.infrastructure.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "price")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  private BrandEntity brand;

  @Column(name = "start_date")
  private ZonedDateTime startDate;

  @Column(name = "end_date")
  private ZonedDateTime endDate;

  @Column(name = "rate_id")
  private Long rateId;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "priority")
  private Integer priority;

  @Column(name = "final_price")
  private BigDecimal finalPrice;

  @Column(name = "ccy")
  private String ccy;
}
