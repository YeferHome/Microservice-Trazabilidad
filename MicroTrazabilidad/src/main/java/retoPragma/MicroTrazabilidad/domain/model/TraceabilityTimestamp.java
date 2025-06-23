package retoPragma.MicroTrazabilidad.domain.model;

import java.time.LocalDateTime;

public class TraceabilityTimestamp {
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private final int second;

    public TraceabilityTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = now.getHour();
        this.minute = now.getMinute();
        this.second = now.getSecond();
    }

    public TraceabilityTimestamp(String isoTimestamp) {
        LocalDateTime dateTime = LocalDateTime.parse(isoTimestamp);
        this.year = dateTime.getYear();
        this.month = dateTime.getMonthValue();
        this.day = dateTime.getDayOfMonth();
        this.hour = dateTime.getHour();
        this.minute = dateTime.getMinute();
        this.second = dateTime.getSecond();
    }

    public TraceabilityTimestamp(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public static long secondsBetween(TraceabilityTimestamp start, TraceabilityTimestamp end) {
        return java.time.Duration.between(
                start.toLocalDateTime(),
                end.toLocalDateTime()
        ).getSeconds();
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public int getSecond() { return second; }
}
