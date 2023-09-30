package com.hkjava.demo.demofinnhub.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// database ?
// -> transactions (real life)
// -> Stock Price per day per stock (Project)
@Getter
@Setter
@AllArgsConstructor
public class SourcePoint {

  public static Map<String, List<SourcePoint>> sourceMap = new HashMap<>();

  // in real life, this should be Interval.MIN_1
  private Interval interval = Interval.DAY;

  private Price closePrice;

  private TranDayTime tranDayTime;

  public Point toPoint() {
    return Point.builder() //
        .closePrice(this.closePrice) //
        .tranDateTime(this.tranDayTime) //
        .build();
  }

}
