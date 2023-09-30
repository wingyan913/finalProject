package com.hkjava.demo.demofinnhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hkjava.demo.demofinnhub.entity.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

}
