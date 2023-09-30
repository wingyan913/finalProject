package com.hkjava.demo.demofinnhub.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hkjava.demo.demofinnhub.config.AppStartRunner;
import com.hkjava.demo.demofinnhub.controller.StockOperation;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.model.dto.web.req.SymbolReqDTO;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.StockDTO;
import com.hkjava.demo.demofinnhub.service.WebStockService;

@RestController
@RequestMapping(value = "/api/v1")
public class StockController implements StockOperation {

  @Autowired
  private WebStockService webStockService;

  @Override
  public ApiResponse<StockDTO> stockInfo(SymbolReqDTO symbol)
      throws FinnhubException {
    return ApiResponse.<StockDTO>builder() //
        .ok() //
        .data(webStockService.stockInfo(symbol.getSymbol())) //
        .build();
  }

}
