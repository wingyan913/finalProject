package com.hkjava.demo.demofinnhub.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.hkjava.demo.demofinnhub.entity.StockSymbol;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.Code;
import com.hkjava.demo.demofinnhub.infra.Protocol;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.QuoteDTO;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.SymbolDTO;
import com.hkjava.demo.demofinnhub.model.mapper.FinnhubMapper;
import com.hkjava.demo.demofinnhub.repository.StockSymbolRepository;
import com.hkjava.demo.demofinnhub.service.StockSymbolService;

@Repository
public class StockSymbolServiceImpl implements StockSymbolService {

  @Autowired
  private StockSymbolRepository symbolRepository;

  @Autowired
  private FinnhubMapper finnhubMapper;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  @Qualifier(value = "finnhubToken")
  private String token;

  @Value(value = "${api.finnhub.domain}")
  private String domain;

  @Value(value = "${api.finnhub.base-url}")
  private String baseUrl;

  @Value(value = "${api.finnhub.endpoints.stock.symbol}")
  private String symbolEndpoint;

  @Override
  public List<SymbolDTO> getAllSymbols() throws FinnhubException {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Protocol.HTTPS.name()) //
        .host(domain) //
        .pathSegment(baseUrl) //
        .path(symbolEndpoint) //
        .queryParam("exchange", "US") //
        .queryParam("token", token) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    try {
      SymbolDTO[] symbols = restTemplate.getForObject(url, SymbolDTO[].class);
      return Arrays.asList(symbols);
    } catch (RestClientException e) {
      throw new FinnhubException(Code.FINNHUB_QUOTE_NOTFOUND);
    }
  }

  @Override
  public List<StockSymbol> save(List<SymbolDTO> symbols) {
    // Convert from Symbols to StockSymbols
    List<StockSymbol> stockSymbols = symbols.stream() //
        .filter(s -> "Common Stock".equals(s.getType()))
        .map(s -> finnhubMapper.map(s)) // convert to Entity
        .collect(Collectors.toList());
    // Save to DB
    return symbolRepository.saveAll(stockSymbols);
  }

  @Override
  public void deleteAll() {
    symbolRepository.deleteAll();
  }

}
