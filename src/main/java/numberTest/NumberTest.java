package numberTest;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberTest {
    public static void main(String[] args) {
        // testIntegerToLong();
        // testNumberCompare();
//        testNumberTurn();
        testNumberStr();
    }

    private static void testIntegerToLong() {
        Integer i = 10;
        Long j;
        long k;

        //		j = i; error
        k = i;

        System.out.println("j = " + k);
    }

    private static void testNumberCompare() {
        System.out.println("NumberUtils.compare(36, 35) = " + NumberUtils.compare(36, 35));
        System.out.println("NumberUtils.compare(36, 36) = " + NumberUtils.compare(36, 36));
        System.out.println("NumberUtils.compare(36, 37) = " + NumberUtils.compare(36, 37));
    }

    private static void testNumberTurn() {
        String year = "-701022";
        String yyyZero = "0000000";

        System.out.println("NumberUtils.toDouble(year) = " + NumberUtils.toDouble(year));
        System.out.println("NumberUtils.toDouble(yyyZero) = " + NumberUtils.toDouble(yyyZero));
        
    }
    
    private static void testNumberStr() {
        String str = "1.0";
        System.out.println(NumberUtils.isNumber(str));
        System.out.println(NumberUtils.toDouble(str));
        System.out.println(NumberUtils.toInt(str));
        
        System.out.println(NumberUtils.toInt(str, 0));
        System.out.println(new BigDecimal(str).intValue());
        
    }
}
