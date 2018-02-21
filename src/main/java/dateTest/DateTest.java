package dateTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

    public static void main(String[] args) {
        //        testDateSimpleFormat();
        //        testDate();
        //        testDatePreviousMonth();
        //        testDateAddDay();
        testRunTime();
    }

    private static void testDateSimpleFormat() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            System.out.println(df.parse("20120531"));
            System.out.println(df.format(df.parse("20120531")));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Calendar.MONTH 從0開始.
     */
    private static void testDate() {
        try {
            Calendar c = new GregorianCalendar();

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

            Date date = df.parse("20110131");
            System.out.println(df.format(df.parse("20110131")));

            c.setTime(date);

            System.out.println(df.format(c.getTime()));
            System.out.println("Year-Month-Day " + c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE));

            c.add(Calendar.MONTH, +1);
            System.out.println(df.format(c.getTime()));
            System.out.println("Year-Month-Day " + c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void testDatePreviousMonth() {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());

        System.out.println("Year:Month:Day " + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

        c.add(Calendar.MONTH, -1);
        System.out.println("Year:Month:Day " + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));
    }

    /**
     * 測+20天，會自動換月。 
     */
    private static void testDateAddDay() {
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        System.out.println(c.getTime());
        System.out.println("Year:Month:Day " + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

        c.add(Calendar.DATE, 30);
        System.out.println(c.getTime());
        System.out.println("Year:Month:Day " + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE));

    }

    private static void testRunTime() {
        Date startTime = new Date();

        for (int i = 0; i < 99999999; i++) {
            for (int j = 0; j < 99999; j++) {
                
            }
        }

        Date endTime = new Date();

        System.out.println(endTime.getTime() - startTime.getTime());
        System.out.println((endTime.getTime() - startTime.getTime())*1.0/1000);
    }
}
