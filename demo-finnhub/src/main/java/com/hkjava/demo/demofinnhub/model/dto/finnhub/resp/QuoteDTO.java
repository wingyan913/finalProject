package com.hkjava.demo.demofinnhub.model.dto.finnhub.resp;

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
public class QuoteDTO {

  @JsonProperty(value = "c")
  private double currentPrice;

  @JsonProperty(value = "d")
  private double change;

  @JsonProperty(value = "dp")
  private double percentChange;

  @JsonProperty(value = "h")
  private double dayHigh;

  @JsonProperty(value = "l")
  private double dayLow;

  @JsonProperty(value = "o")
  private double dayOpen;

  @JsonProperty(value = "pc")
  private double prevDayClose;

  @JsonProperty(value = "t")
  private long unixtime;

}
