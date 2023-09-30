package com.hkjava.demo.demofinnhub.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.entity.StockSymbol;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.CompanyProfile2DTO;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.QuoteDTO;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.SymbolDTO;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.CompanyProfileDTO;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.StockDTO;

@Component
public class FinnhubMapper {

  @Autowired
  private ModelMapper modelMapper;

  public StockDTO map(CompanyProfile2DTO companyProfile, QuoteDTO quote) {
    return StockDTO.builder() //
        .companyProfile(
            modelMapper.map(companyProfile, CompanyProfileDTO.class)) //
        .currentPrice(quote.getCurrentPrice()) //
        .dayHigh(quote.getDayHigh()) //
        .dayLow(quote.getDayLow()) //
        .dayOpen(quote.getDayOpen()) //
        .prevDayClose(quote.getPrevDayClose()) //
        .build();
  }

  public StockSymbol map(SymbolDTO symbol) {
    return StockSymbol.builder() //
        .symbol(symbol.getSymbol()) //
        .build();
  }

  public Stock map(CompanyProfile2DTO profile) {
    return Stock.builder() //
        .country(profile.getCountry()) //
        .companyName(profile.getCompanyName()) //
        .logo(profile.getLogo()) //
        .marketCap(profile.getMarketCap()) //
        .currency(profile.getCurrency()) //
        .build();
  }

  public StockPrice map(QuoteDTO quote) {
    return StockPrice.builder() //
        .currentPrice(quote.getCurrentPrice()).dayHigh(quote.getDayHigh())
        .dayHigh(quote.getDayHigh()).dayLow(quote.getDayLow())
        .dayOpen(quote.getDayOpen()).prevDayClose(quote.getPrevDayClose())
        .build();
  }

}
