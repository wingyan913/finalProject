package com.hkjava.demo.demofinnhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.hkjava.demo.demofinnhub.annotation.SymbolCheck;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResponse;
import com.hkjava.demo.demofinnhub.model.dto.web.req.SymbolReqDTO;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.StockDTO;

@Validated
public interface StockOperation {

  @GetMapping(value = "/stock")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<StockDTO> stockInfo(@SymbolCheck @RequestParam("symbol") SymbolReqDTO symbol)
      throws FinnhubException;

  // @GetMapping(value = "/stockfromdb")
  // @ResponseStatus(value = HttpStatus.OK)
  // ApiResponse<StockDTO> stockInfoFromDb(@RequestParam("symbol") String symbol)
  //     throws FinnhubException;

}
