package se.liu.ida.antbe028.calendar;

import java.util.ArrayList;
import java.util.List;

public class Cal {

    public static void main(String[] args) {
        Cal antonCal = new Cal();
        antonCal.book(2020, "Januari", 21, 12, 0, 14, 30, "Lunchmöte");
        antonCal.show();
        antonCal.book(2010, "Mars", 10, 8, 15, 9, 45, "Åk skidor");
        antonCal.book(2019, "Augusti", 14, 5, 0, 10, 23, "Spela TV-spel");
        antonCal.book(2003, "December", 3, 23, 35, 23, 55, "Studera");
        antonCal.book(1999, "Maj", 20, 7, 0, 10, 0, "Telefonmöte");
        antonCal.book(3003, "Oktober", 30, 17, 55, 18, 30, "Boka in fler möten");

        antonCal.show();

        // Testar olika felaktiga inputs
        //antonCal.book(1969, "Juli", 21, 12, 0, 14, 30, "Gör ingenting"); // Felaktig år
        //antonCal.book(2020, "Felaktig-månad", 21, 12, 0, 14, 30, "Gör ingenting"); // Felaktig månad
        //antonCal.book(2020, "Februari", 54, 12, 0, 14, 30, "Gör ingenting"); // Felaktig antal dagar i Februari
        //antonCal.book(2020, "Augusti", 21, 190, 0, 14, 30, "Gör ingenting"); // starthour kan inte vara 190
        //antonCal.book(2020, "Maj", 21, 12, 0, 14, -300, "Gör ingenting"); // endMinute kan inte vara -300
    }

    private List<Appointment> appointments;

    public Cal() {
        appointments = new ArrayList<>();
    }

    public void show() {
        System.out.println();
        for (Appointment element : appointments) {
            System.out.println(element);
        }
    }

    public void book(int year, String month, int day,
        int startHour, int startMinute, int endHour, int endMinute, 
        String subject) {

            Month newMonth = new Month(month, Month.getMonthNumber(month), 
                Month.getMonthDays(month));
            SimpleDate newSimpleDate = new SimpleDate(year, newMonth, day);

            if (isaBoolean(year, month, day, startHour, startMinute, endHour, endMinute, newMonth)) {

                    TimePoint timeSpanStart = new TimePoint(startHour, startMinute);
                    TimePoint timeSpanEnd = new TimePoint(endHour, endMinute);
                    TimeSpan newTS = new TimeSpan(timeSpanStart, timeSpanEnd);
                    
                    Appointment newApp = new Appointment(subject, newSimpleDate,
                        newTS);
                    appointments.add(newApp);
            }

            else {
                throw new IllegalArgumentException("felmeddelande");
            }
        }


    private boolean isaBoolean(final int year, final String month, final int day, final int startHour, final int startMinute,
                               final int endHour, final int endMinute, final Month newMonth)
    {
        return year > 1970 && 0 <= startHour && startHour <= 23 && 0 <= endHour && endHour <= 23 && 0 <= startMinute && startMinute <= 59 &&
               0 <= endMinute && endMinute <= 59 && Month.getMonthNumber(month) != -1 && Month.getMonthDays(month) != -1 && 0 <= day &&
               day <= newMonth.getDays();
    }
}
