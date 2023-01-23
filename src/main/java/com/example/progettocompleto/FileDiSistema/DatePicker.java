package com.example.progettocompleto.FileDiSistema;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class DatePicker implements Runnable{



       /* private static Clock clock = Clock.systemDefaultZone();
        private static Instant instant = clock.instant();
        private static ZoneId zone = clock.getZone();
        private static ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zone);
*/

    private static Clock clock;
    private static Instant instant;
    private static ZoneId zone;
    private static ZonedDateTime zdt;
    private static int minute;
    private static int second;
    private static int day;
    private static int month;
    private static int year;



    Timer timer = new Timer();


    @Override
    public void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                clock = Clock.systemDefaultZone();
                instant = clock.instant();
                zone = clock.getZone();
                zdt = ZonedDateTime.ofInstant(instant, zone);
                second = zdt.getSecond();
                minute = zdt.getMinute();
                day = zdt.getDayOfMonth();
                month = zdt.getMonthValue();
                year = zdt.getYear();

            }

        }, 0, 1000);
    }






    public static int getHour () {
        int hour = zdt.getHour();
        return hour;
    }

    public static int getMinute () {
        return minute;
    }

    public static int getSecond () {
        int second = zdt.getSecond();
        return second;
    }
    public static int getMonth() {
        return month;
    }

    public static int getYear() {
        return year;
    }

    public static int getDay() {
        return day;
    }

}
