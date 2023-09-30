package com.hkjava.demo.demofinnhub.model.dto.finnhub.resp;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.hkjava.demo.demofinnhub.infra.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class CompanyProfile2DTO implements Serializable {
  
  private String country;

  private String currency;

  private String estimateCurrency;

  private String exchange;

  private String finnhubIndustry;

  @JsonProperty(value = "ipo")
  private LocalDate ipoDate;

  private String logo;

  @JsonProperty(value = "marketCapitalization")
  private double marketCap;

  @JsonProperty(value = "name")
  private String companyName;

  private String phone;

  private double shareOutstanding;

  private String ticker;

  private String weburl;

  

}
