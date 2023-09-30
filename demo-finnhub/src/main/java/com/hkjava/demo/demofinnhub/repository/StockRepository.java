package com.hkjava.demo.demofinnhub.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockSymbol;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

  // select * from stocks where country = ? and market_cap > ?;
  List<Stock> findFirst3ByCountryAndMarketCapGreaterThanEqualOrderByIdDesc(
      String country, double marketCap);

  // update stocks set field = x where field = ?
  // solution: (Put/ Patch) findById() -> set() -> save()

  // Native SQL query
  @Query(
      value = "select s.id, s.country, s.company_name, s.logo, s.ipo_date, s.market_cap, s.currency from finnhub_stocks s where s.id = :id",
      nativeQuery = true)
  List<Stock> findAllById2(@Param(value = "id") Long id);

  // JPQL (Java Persistence query language)
  @Query(value = "select s from Stock s where s.id = :id")
  List<Stock> findAllById3(@Param(value = "id") Long id);

  Optional<Stock> findByStockSymbol(StockSymbol stockSymbol);

}
