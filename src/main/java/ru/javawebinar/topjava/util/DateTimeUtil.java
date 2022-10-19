package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static LocalDateTime getStartMinDateTime(LocalDate startDate) {
        return startDate != null ? startDate.atStartOfDay() : LocalDateTime.MIN;
    }

    public static LocalDateTime getEndMaxDateTime(LocalDate endDate) {
        return endDate != null ? endDate.plus(1, ChronoUnit.DAYS).atStartOfDay() : LocalDateTime.MAX;
    }

    public static LocalTime getStartMinTime(LocalTime startTime) {
        return startTime != null ? startTime : LocalTime.MIN;
    }

    public static LocalTime getStartMaxTime(LocalTime startTime) {
        return startTime != null ? startTime : LocalTime.MAX;
    }

    public static LocalDate parseDate(String str) {
        return str.isEmpty() ? null : LocalDate.parse(str);
    }

    public static LocalTime parseTime(String str) {
        return str.isEmpty() ? null : LocalTime.parse(str);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

