package com.hkjava.demo.demofinnhub.service;

import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.StockDTO;

public interface WebStockService {
  
  StockDTO stockInfo(String symbol) throws FinnhubException;

}
