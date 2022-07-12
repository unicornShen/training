package BigDecimalTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class BigDecimalTest {
    public static void main(String[] args) {

        // testHalfUp();
        // testHalfUp2();
        // testC();
        // testMathContext();
        // testSetScale();
        // testD();
        // testD_final();
        // testGetMillion();

        // testToInt();
        // testNumberFormat();
        // testError();

        testMultiply();
    }

    private static void testHalfUp() {
        BigDecimal ba = new BigDecimal("0.105", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("105 = " + ba);
        ba = new BigDecimal("0.115", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("115 = " + ba);
        ba = new BigDecimal("0.125", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("125 = " + ba);
        ba = new BigDecimal("0.135", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("135 = " + ba);
        ba = new BigDecimal("0.145", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("145 = " + ba);
        ba = new BigDecimal("0.155", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("155 = " + ba);
        ba = new BigDecimal("0.165", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("165 = " + ba);
        ba = new BigDecimal("0.175", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("175 = " + ba);
        ba = new BigDecimal("0.185", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("185 = " + ba);
        ba = new BigDecimal("0.195", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println("195 = " + ba);

        System.out.println(BigDecimalTest.round(new BigDecimal("0.105"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.115"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.125"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.135"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.145"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.155"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.165"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.175"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.185"), 2));
        System.out.println(BigDecimalTest.round(new BigDecimal("0.195"), 2));

    }

    public static BigDecimal round(BigDecimal value, int scale) {
        if (value != null) {

            if (scale < 0) {
                scale = 2; // 小於零就預設2
            }
            return value.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);

        } else {
            return null;
        }
    }

    /** 四捨五入至整數. */
    private static void testHalfUp2() {
        BigDecimal scaled = new BigDecimal("123456.54321").setScale(0, RoundingMode.HALF_UP);
        System.out.println(scaled);
    }

    private static void testC() {
        System.out.println(BigDecimal.ZERO.compareTo(new BigDecimal("100")));
        System.out.println(BigDecimal.ZERO.compareTo(new BigDecimal("0")));
        System.out.println(BigDecimal.ZERO.compareTo(new BigDecimal("-100")));
    }

    private static void testMathContext() {
        int s = 1945;

        // 3表示取四位有效数字，RoundingMode.HALF_UP表示四舍五入
        final MathContext roundingMode = new MathContext(3, RoundingMode.HALF_UP);
        final BigDecimal size = new BigDecimal(ObjectUtils.toString(s, "0"));

        final BigDecimal ans = size.divide(BigDecimal.TEN, roundingMode);

        System.out.println(ans.toString());
    }

    private static void testSetScale() {
        int s = 194;

        // 有計算時用這個比較準
        final BigDecimal size = new BigDecimal(ObjectUtils.toString(s, "0"));
        final BigDecimal ans = size.divide(BigDecimal.TEN, 0, BigDecimal.ROUND_HALF_UP);
        System.out.println(ans.toString());

        // 單純取到小是第幾位用這個
        final BigDecimal amt = new BigDecimal("123.456");
        System.out.println(amt.setScale(1, RoundingMode.HALF_UP));
    }

    private static void testD() {
        int s = 97;

        final BigDecimal size = new BigDecimal(ObjectUtils.toString(s, "0"));
        final BigDecimal ans1 = size.multiply(new BigDecimal("0.1")).subtract(BigDecimal.ZERO).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans1);

        final BigDecimal ans2 = size.multiply(new BigDecimal("0.2")).subtract(ans1).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans2);

        final BigDecimal ans3 = size.multiply(new BigDecimal("0.3")).subtract(ans1).subtract(ans2).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans3);

        final BigDecimal ans4 = size.multiply(new BigDecimal("0.4")).subtract(ans1).subtract(ans2).subtract(ans3).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans4);

        final BigDecimal ans5 = size.multiply(new BigDecimal("0.5")).subtract(ans1).subtract(ans2).subtract(ans3).subtract(ans4).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans5);

        final BigDecimal ans6 = size.multiply(new BigDecimal("0.6")).subtract(ans1).subtract(ans2).subtract(ans3).subtract(ans4).subtract(ans5).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans6);

        final BigDecimal ans7 = size.multiply(new BigDecimal("0.7")).subtract(ans1).subtract(ans2).subtract(ans3).subtract(ans4).subtract(ans5).subtract(ans6).setScale(0,
                RoundingMode.HALF_UP);
        System.out.println(ans7);

        final BigDecimal ans8 = size.multiply(new BigDecimal("0.8")).subtract(ans1).subtract(ans2).subtract(ans3).subtract(ans4).subtract(ans5).subtract(ans6).subtract(ans7)
                .setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans8);

        final BigDecimal ans9 = size.multiply(new BigDecimal("0.9")).subtract(ans1).subtract(ans2).subtract(ans3).subtract(ans4).subtract(ans5).subtract(ans6).subtract(ans7)
                .subtract(ans8).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans9);

        final BigDecimal ans10 = size.multiply(new BigDecimal("1.0")).subtract(ans1).subtract(ans2).subtract(ans3).subtract(ans4).subtract(ans5).subtract(ans6).subtract(ans7)
                .subtract(ans8).subtract(ans9).setScale(0, RoundingMode.HALF_UP);
        System.out.println(ans10);

    }

    private static void testD_final() {

        final BigDecimal baseRange = new BigDecimal("0.1");

        int s = 1;
        final BigDecimal size = new BigDecimal(ObjectUtils.toString(s, "0"));

        final List<BigDecimal> rowList = new ArrayList<>();
        for (BigDecimal i = baseRange; i.compareTo(BigDecimal.ONE) <= 0; i = i.add(baseRange)) {
            BigDecimal value = size.multiply(i).subtract(getTotal(rowList)).setScale(0, RoundingMode.HALF_UP);
            rowList.add(value);
        }

        for (BigDecimal row : rowList) {
            System.out.println(row);
        }

        // for (int i = rowList.size(); i >= 1; i--) {
        // System.out.println(i);
        // }
    }

    private static BigDecimal getTotal(final List<BigDecimal> list) {
        BigDecimal result = BigDecimal.ZERO;

        for (BigDecimal row : list) {
            result = result.add(row);
        }

        // System.out.println("result = " + result);
        return result;
    }

    private static void testGetMillion() {
        final BigDecimal number = new BigDecimal("1234567890");

        System.out.println(number.toString());

        final BigDecimal million = new BigDecimal("1000000");
        System.out.println(number.divide(million, 0, RoundingMode.HALF_UP));
    }

    private static void testToInt() {
        final BigDecimal number = new BigDecimal("0.123");

        System.out.println(number.floatValue());
    }

    /**
     * 格式化
     */
    private static void testNumberFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(); // 建立貨幣格式化引用
        currency.setParseIntegerOnly(true);
        currency.setRoundingMode(RoundingMode.HALF_UP);
        currency.setMinimumFractionDigits(4); // 最小 小數點位數

        NumberFormat percent = NumberFormat.getPercentInstance(); // 建立百分比格式化引用
        percent.setMaximumFractionDigits(3); // 百分比小數點最多3位

        BigDecimal loanAmount = new BigDecimal("123456789.485555"); // 貸款金額
        BigDecimal interestRate = new BigDecimal("0.008"); // 利率
        BigDecimal interest = loanAmount.multiply(interestRate); // 相乘

        System.out.println("貸款金額:\t" + currency.format(loanAmount)); // 貸款金額: ￥150.48
        System.out.println("利率:\t" + percent.format(interestRate)); // 利率: 0.8%
        System.out.println("利息:\t" + currency.format(interest)); // 利息: ￥1.20
    }

    private static void testError() {
        final NumberFormat currTwd = NumberFormat.getCurrencyInstance();
        currTwd.setMaximumFractionDigits(0);
        currTwd.setMinimumFractionDigits(0);

        BigDecimal amount = new BigDecimal("123456789.485555");

        System.out.println(StringUtils.replaceOnce(currTwd.format(null), "$", ""));
    }

    private static void testMultiply() {
        BigDecimal amount = new BigDecimal("0.00025");
        BigDecimal rate = new BigDecimal("100");
        BigDecimal result = amount.multiply(rate); // 相乘
        System.out.println(result);

        final DecimalFormat df = new DecimalFormat("#.#####");
        System.out.println(df.format(result));
    }

}
