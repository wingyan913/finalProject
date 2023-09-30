package com.hkjava.demo.demofinnhub.model.dto.finnhub.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolDTO {
  
  private String currency;

  @JsonProperty("description")
  private String desc;

  private String displaySymbol;
  
  @JsonProperty("figi")
  private String figiIdentifier;

  private String isin;

  @JsonProperty("mic")
  private String exchangeMic;

  private String shareClassFIGI;
  
  private String symbol;

  @JsonProperty("symbol2")
  private String altSymbol;
  
  private String type;
}
