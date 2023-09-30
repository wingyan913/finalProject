package com.hkjava.demo.demofinnhub.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class Line {

  private Interval interval; // MONTH

  private String symbol;

  private Queue<Point> closePoints; // per month

  public Line(String symbol, Interval interval) { // DAY, WEEK, MONTH
    this.interval = interval; // month
    this.symbol = symbol;
    this.closePoints = new LinkedList<>();
    Comparator<SourcePoint> reversed =
        (SourcePoint p1, SourcePoint p2) -> p1.getTranDayTime().getDatetime()
            .isAfter(p2.getTranDayTime().getDatetime()) ? -1 : 1;
    SourcePoint.sourceMap.get(symbol).stream() //
        .filter(s -> {
          if (interval == Interval.WEEK)
            return s.getTranDayTime().isWeeklyClose();
          else if (interval == Interval.MONTH)
            return s.getTranDayTime().isMonthlyClose();
          else // day
            return true;
        }).sorted(reversed) //
        .forEach(s -> {
          // filtered source points, add to closePoints
          closePoints.add(s.toPoint());
        });
  }

  // instance method -> calculate
  // public List<Point> movingAverage(int noOfInterval) {
  // int count = 0;
  // BigDecimal first =

  // while(count < noOfInterval) {

  // count++;
  // }
  // }


  public List<Point> movingAverage(int noOfInterval) {

    List<Point> movingAvgLine = new LinkedList<>();

    List<Point> copyOfClosePoints = new LinkedList<>(this.closePoints);
    List<Point> noOfIntervalPoints = copyOfClosePoints.stream() //
        .limit(noOfInterval) //
        .collect(Collectors.toList());

    while (copyOfClosePoints.size() >= noOfInterval) {
      BigDecimal sumOfPrices = BigDecimal.ZERO;

      for (Point point : noOfIntervalPoints) {
        BigDecimal price = BigDecimal.valueOf(point.getClosePrice().getPrice());
        sumOfPrices = sumOfPrices.add(price);
      }

      BigDecimal avg = sumOfPrices //
          .divide(BigDecimal.valueOf(noOfInterval), RoundingMode.HALF_UP) //
          .setScale(3, RoundingMode.HALF_UP);

      Point avgPrice = Point.builder().closePrice(Price.builder() //
          .price(avg.doubleValue()).build()) //
          .tranDateTime(noOfIntervalPoints.get(noOfIntervalPoints.size() - 1).getTranDateTime()) //
          .build();

      movingAvgLine.add(avgPrice);

      noOfIntervalPoints.remove(0);
      noOfIntervalPoints.add(copyOfClosePoints.get(noOfInterval));
      copyOfClosePoints.remove(0);
    }

    return movingAvgLine;
  }

  // Override the Getter
  public List<Point> getClosePoints() {
    List<Point> points = new ArrayList<>();
    points.addAll(this.closePoints);
    return points;
  }

}
