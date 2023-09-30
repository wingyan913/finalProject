package com.hkjava.demo.demofinnhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.hkjava.demo.demofinnhub.entity.Stock;
import com.hkjava.demo.demofinnhub.entity.StockPrice;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.Code;
import com.hkjava.demo.demofinnhub.infra.Protocol;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.QuoteDTO;
import com.hkjava.demo.demofinnhub.repository.StockPriceRepository;
import com.hkjava.demo.demofinnhub.repository.StockRepository;
import com.hkjava.demo.demofinnhub.service.StockPriceService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StockPriceServiceImpl implements StockPriceService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private StockPriceRepository stockPriceRepository;

  @Autowired
  @Qualifier(value = "finnhubToken")
  private String token;

  @Value(value = "${api.finnhub.domain}")
  private String domain;

  @Value(value = "${api.finnhub.base-url}")
  private String baseUrl;

  @Value(value = "${api.finnhub.endpoints.stock.quote}")
  private String quoteEndpoint;

  @Override
  public QuoteDTO getQuote(String symbol) throws FinnhubException {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Protocol.HTTPS.name()) //
        .host(domain) //
        .pathSegment(baseUrl) //
        .path(quoteEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", token) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    try {
      return restTemplate.getForObject(url, QuoteDTO.class);
    } catch (RestClientException e) {
      throw new FinnhubException(Code.FINNHUB_QUOTE_NOTFOUND);
    }
  }

  @Override
  public StockPrice save(Long id, StockPrice stockPrice) {
    Stock stock = stockRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Stock ID Not Found."));
    stockPrice.setStock(stock);
    return stockPriceRepository.save(stockPrice);
  }

}
