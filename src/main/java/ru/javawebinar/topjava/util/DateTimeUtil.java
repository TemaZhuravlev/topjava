package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) < 0;
    }

    public static LocalDateTime getThisOrMinDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : LocalDateTime.MIN;
    }

    public static LocalDateTime getNextOrMaxDateTime(LocalDate date) {
        return date != null ? date.plus(1, ChronoUnit.DAYS).atStartOfDay() : LocalDateTime.MAX;
    }

    public static LocalTime getThisOrMinTime(LocalTime time) {
        return time != null ? time : LocalTime.MIN;
    }

    public static LocalTime getThisOrMaxTime(LocalTime time) {
        return time != null ? time : LocalTime.MAX;
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

