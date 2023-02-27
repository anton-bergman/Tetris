package se.liu.ida.antbe028.calendar;

public class SimpleDate {
    public static void main(String[] args) {
        Month month = new Month("Januari", 1, 31);
        SimpleDate testSimpleDate = new SimpleDate(2020, month, 20);
        System.out.println(testSimpleDate.getYear());
    }
    
    private int year;
    private Month month;
    private int day;

    public SimpleDate(final int year, final Month month, final int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String toString() {
        return year + "-" + month.getNumber() + "-" + month.getDays();
    }
}

