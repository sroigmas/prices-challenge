package com.github.sroigmas.priceschallenge.infrastructure.jpa;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

  @Query(
      """
      select p from price p where p.brand.id = :brandId
      and p.productId = :productId
      and p.startDate <= :date
      and p.endDate >= :date
      """)
  List<PriceEntity> findPricesByIdsAndDate(Long brandId, Long productId, ZonedDateTime date);
}
