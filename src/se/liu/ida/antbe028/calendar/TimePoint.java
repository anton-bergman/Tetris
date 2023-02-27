package se.liu.ida.antbe028.calendar;

public class TimePoint {
    
    private int hour;
    private int minute;

    public TimePoint(final int hour, final int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String toString() {
        return hour + ":" + minute;
    }
}
