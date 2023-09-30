package com.hkjava.demo.demofinnhub.service;

import java.util.List;
import com.hkjava.demo.demofinnhub.entity.StockSymbol;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.SymbolDTO;

public interface StockSymbolService {

  List<SymbolDTO> getAllSymbols() throws FinnhubException;

  List<StockSymbol> save(List<SymbolDTO> symbols);

  void deleteAll();

}
