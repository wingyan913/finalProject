package com.hkjava.demo.demofinnhub.model.dto.web.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockDTO {
  
  private CompanyProfileDTO companyProfile;
  
  private double currentPrice;
  
  private double dayHigh;
  
  private double dayLow;
  
  private double dayOpen;
  
  private double prevDayClose;

}
