package com.hkjava.demo.demofinnhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
public class Point {

  private Price closePrice;

  private TranDayTime tranDateTime;

}
