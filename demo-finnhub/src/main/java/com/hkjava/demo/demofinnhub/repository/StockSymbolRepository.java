package com.hkjava.demo.demofinnhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hkjava.demo.demofinnhub.entity.StockSymbol;

@Repository
public interface StockSymbolRepository extends JpaRepository<StockSymbol, Long> {
  
}
