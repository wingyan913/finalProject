package com.hkjava.demo.demofinnhub.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TranDayTime {

  private LocalDate datetime;

  // Assume Friday is not holiday
  public boolean isWeeklyClose() {
    return datetime.getDayOfWeek() == DayOfWeek.FRIDAY;
  }

  public boolean isMonthlyClose() {
    return this.datetime.getDayOfMonth() == lastTranDayOfMonth(
        datetime.getMonth(), datetime.getYear());
  }

  // Ignore Leap Year handling at the moment
  private static int lastTranDayOfMonth(Month month, int year) {
    if (year < 1980 || year > 2100)
      return -1;
    int dd = switch (month) {
      case JANUARY -> 31;
      case FEBRUARY -> 28;
      case MARCH -> 31;
      case APRIL -> 30;
      case MAY -> 31;
      case JUNE -> 30;
      case JULY -> 31;
      case AUGUST -> 31;
      case SEPTEMBER -> 30;
      case OCTOBER -> 31;
      case NOVEMBER -> 30;
      case DECEMBER -> 31;
      default -> -1;
    };
    LocalDate lastTranDate = LocalDate.of(year, month.getValue(), dd);
    while (lastTranDate.getDayOfWeek() == DayOfWeek.SATURDAY
        || lastTranDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
      lastTranDate = lastTranDate.minusDays(1L);
    }
    return lastTranDate.getDayOfMonth();
  }
}
