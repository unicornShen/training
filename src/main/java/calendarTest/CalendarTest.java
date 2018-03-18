package calendarTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTest {
    public static void main(String[] s) {
        //      Calendar.SUNDAY;
        //      Calendar.MONDAY;
        //      Calendar.TUESDAY;
        //      Calendar.WEDNESDAY;
        //      Calendar.THURSDAY;
        //      Calendar.FRIDAY;
        //      Calendar.SATURDAY;
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());
        System.out.println("c.get() = " + c.get(Calendar.DAY_OF_WEEK));

        Calendar cc = Calendar.getInstance();
        cc.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println("cc.get() = " + cc.get(Calendar.DAY_OF_WEEK));

        //---------------------------------------------
        //------
        //---------------------------------------------
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();

        Date trialTime = new Date();
        System.out.println("new Date format = " + sdf.format(trialTime));

        calendar.setTime(trialTime);
        System.out.println("取得時區 ERA: " + calendar.get(Calendar.ERA));
        System.out.println("取得現在的年份 YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("取得現在的月份 MONTH: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("取得現在的月份是有幾週 WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("取得今天幾號 DATE: " + calendar.get(Calendar.DATE));
        System.out.println("取得今天是這個月份的幾號 DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("取得今天是這星期的第幾天 DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("取得今天是這個月份中的第幾週 DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("取得現在時間是上午AM還是下午PM AM_PM: " + calendar.get(Calendar.AM_PM));
        System.out.println("取得現在時間幾點(12小時制) HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("取得現在時間幾點 (24小時制) HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("取得現在時間是幾分 MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("取得現在時間幾秒 SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("取得現在時間豪秒 MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("ZONE_OFFSET: " + (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
        System.out.println("DST_OFFSET: " + (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000)));
        System.out.println("取得今天是1年中的第幾天 DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("取得今天是1年中的第幾週 WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));

        System.out.println("改變時間 , 改成指定為 下午3點");
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR, 3);

        System.out.println("取得時區 ERA: " + calendar.get(Calendar.ERA));
        System.out.println("取得現在的年份 YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("取得現在的月份 MONTH: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("取得現在的月份是有幾週 WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("取得今天幾號 DATE: " + calendar.get(Calendar.DATE));
        System.out.println("取得今天是這個月份的幾號 DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("取得今天是這星期的第幾天 DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("取得今天是這個月份中的第幾週 DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("取得現在時間是上午AM還是下午PM AM_PM: " + calendar.get(Calendar.AM_PM));
        System.out.println("取得現在時間幾點(12小時制) HOUR: " + calendar.get(Calendar.HOUR));
        System.out.println("取得現在時間幾點 (24小時制) HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("取得現在時間是幾分 MINUTE: " + calendar.get(Calendar.MINUTE));
        System.out.println("取得現在時間幾秒 SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println("取得現在時間豪秒 MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("ZONE_OFFSET: " + (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
        System.out.println("DST_OFFSET: " + (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000)));
        System.out.println("取得今天是1年中的第幾天 DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("取得今天是1年中的第幾週 WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));

        calendar.set(2010, 7, 0);
        System.out.println("取得所指定月份有幾天? " + calendar.get(Calendar.DAY_OF_MONTH));
        
        
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        System.out.println(sdf.format(getFirstMonthDay(calendar)));
        System.out.println(sdf.format(getLastMonthDay(calendar)));

    }

    //每個月的第一天日期
    public static Date getFirstMonthDay(Calendar calendar) {
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    //每個月的最後一天日期
    public static Date getLastMonthDay(Calendar calendar) {
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    //  public static void main(String[] s) {
    //            System.out.println(StringUtils.substring("10301", 0, 3));
    //            System.out.println(StringUtils.substring("10301", 3, 5));
    //    
    //            Calendar calendar = new GregorianCalendar(1911 + 102, 12, 0);
    //    
    //            // +2 month.
    //            calendar.set(1911 + 103, 2, 0);
    //            calendar.add(Calendar.MONTH, -2);
    //            System.out.println("ROC date = " + RocYYYMMDDUtils.format(calendar.getTime()).substring(0, 5));
    //    
    //            // +1 year.
    //            calendar.set(1911 + 103, 2, 0);
    //            calendar.add(Calendar.YEAR, -1);
    //            System.out.println("ROC date = " + RocYYYMMDDUtils.format(calendar.getTime()).substring(0, 5));
    //    
    //            // +12 day.
    //            calendar.set(1911 + 103, 2, 0);
    //            calendar.add(Calendar.DATE, -12);
    //            System.out.println("ROC date = " + RocYYYMMDDUtils.format(calendar.getTime()));
    //            calendar.set(1911 + 103, 2, 0);
    //            calendar.add(Calendar.DATE, -11);
    //            System.out.println("ROC date = " + RocYYYMMDDUtils.format(calendar.getTime()));
    //    
    //            // +3 month.
    //            calendar.set(1911 + 103, 2, 0);
    //            calendar.add(Calendar.MONTH, -3);
    //            System.out.println("ROC date = " + RocYYYMMDDUtils.format(calendar.getTime()).substring(0, 5));
    //    
    //            // +4 month.
    //            calendar.set(1911 + 103, 2, 0);
    //            calendar.add(Calendar.MONTH, -4);
    //            System.out.println("ROC date = " + RocYYYMMDDUtils.format(calendar.getTime()).substring(0, 5));
    //        }
}
