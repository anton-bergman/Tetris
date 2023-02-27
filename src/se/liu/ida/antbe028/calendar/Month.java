package se.liu.ida.antbe028.calendar;

public class Month {
    
    private String name;
    private int number;
    private int days;

    public Month(final String name, final int number, final int days) {
        this.name = name;
        this.number = number;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getDays() {
        return days;
    }

    public static int getMonthNumber(String name) {
        switch (name) {
            case "Januari":
                return 1;
            case "Februari":
                return 2;
            case "Mars":
                return 3;
            case "April":
                return 4;
            case "Maj":
                return 5;
            case "Juni":
                return 6;
            case "Juli":
                return 7;
            case "Augusti":
                return 8;
            case "September":
                return 9;
            case "Oktober":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return -1;
        }
    }

    public static int getMonthDays(String name) {
        switch (name) {
            case "Januari":
	    case "Mars":
            case "Maj":
            case "Juli":
            case "Augusti":
            case "Oktober":
            case "December":
                return 31;
            case "Februari":
                return 28;
	    case "April":
            case "Juni":
            case "September":
            case "November":
                return 30;
            default:
                return -1;
        }
    }
}
