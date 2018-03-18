package StringTest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class StringTest {

    public static void main(String[] args) {
        // StringBuilder sb = new StringBuilder();
        // sb.append("CONT_NAME = 'XX壹', CONT_RELATE = '同事', CONT_TEL_COUNTRY='886' , CONT_TEL = '02123456789 ', CONT_TEL_EXT = '2345'");
        // genInsert(sb.toString());
        // genSelect(sb.toString());
        // testMap();
        // testTrim();
        // testToUpperCase();
        // testStringBuilderLength();
        // testLeftPed();
        // testStringComperator();
        // testSplit();
        // testSplitAfter11();
        // testSplit1();

        //         testMatches();

        //        positiveNumber();

        Object object = 123;
        System.out.println(ObjectUtils.identityToString(object));
        System.out.println(ObjectUtils.toString(object));
        System.out.println(ObjectUtils.defaultIfNull(object, "default Value"));
    }

    public static void genInsert(String str) {
        String[] strArray = str.split(",");
        String sql = "";
        String tab = "";
        for (String s : strArray) {
            s.indexOf("=");
            sql += s.substring(s.indexOf("=") + 1, s.length()) + ",";
            System.out.println(sql);
            tab += s.substring(0, s.indexOf("=") - 1) + ", ";
            System.out.println(tab);
        }

    }

    public static void genSelect(String str) {
        String[] strArray = str.split(",");
        String sql = "";
        for (String s : strArray) {
            sql += s + "and";
            System.out.println(sql);
        }
    }

    public static void testMap() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        System.out.println(map.keySet().toString());
    }

    public static void testTrim() {
        String str = "   ddd     ccc   hhh    jjj     kk";
        StringBuilder reStr = new StringBuilder();
        String[] strArray = str.split(" ");
        for (String s : strArray) {
            System.out.println(s);
            reStr.append(s);
        }
        System.out.println(reStr.toString());
    }

    public static void testToUpperCase() {
        String str = "ＡＳＣ＄＃＆";
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
    }

    public static void testStringBuilderLength() {
        StringBuilder sb = new StringBuilder();
        String returnStr = "";
        sb.append("AA");
        sb.append("BB");
        sb.append("CC");
        sb.append("DD");
        System.out.println(sb.length());
        if (sb.length() > 3) {
            returnStr = sb.substring(0, 3);
        }
        System.out.println(returnStr);
    }

    public static void testLeftPed() {
        String testStr = "123";
        testStr = StringUtils.leftPad(testStr, 5, "0");

        System.out.println("testStr = " + testStr);
    }

    public static void testStringComperator() {
        String testStr1 = "123";
        String testStr2 = "129";

        System.out.println(testStr1.compareTo(testStr2));
    }

    public static void testSplit() {
        String content = "001,002,003,004,";
        List<String> array = new ArrayList<String>();

        String[] strArray = content.split(",");
        System.out.println("strArray.length = " + strArray.length);
        for (String str : strArray) {
            System.out.println("str=" + str);
            array.add(str);
        }

        array.remove("002");
        System.out.println("array.size = " + array.size());
        for (String str : array) {
            System.out.println("str=" + str);
        }
    }

    public static void testSplitAfter11() {
        String content = "役署甄字第　　　1020323004";
        System.out.println("content.length = " + content.length());
        System.out.println(StringUtils.substring(content, content.length() - 11, content.length()));
        System.out.println(StringUtils.substring(content, 0, content.length() - 11));

        String content2 = "91020323004";
        System.out.println("content2.length = " + content2.length());
        System.out.println(StringUtils.substring(content2, content2.length() - 11));
        System.out.println(StringUtils.substring(content2, 0, content2.length() - 11));
    }

    public static void testSplit1() {
        // /pages/func/ml2000/ml2j00/ml2j20/ml2j20.xhtml?faces-redirect=true
        String str = "2j20";
        String[] array = str.split("");

        for (String s : array) {
            System.out.println("s = " + s);
        }

        System.out.println(String.format("/pages/func/ml%s000/ml%s00/ml%s0/ml%s.xhtml?faces-redirect=true" //
                , array[1], array[1] + array[2], array[1] + array[2] + array[3], array[1] + array[2] + array[3] + array[4]));
    }

    public static void testMatches() {
        String str = "2";
        System.out.println(str.matches("[1-3]"));
        System.out.println(str.matches("[(1-3)]"));
        System.out.println(str.matches("(123)|(2)"));
        System.out.println(str.matches("2"));

        System.out.println("123 : [^#&@]* = " + "123".matches("[^#&@]*"));
        System.out.println("12#3 : [^#&@]* = " + "12#3".matches("[^#&@]*"));
        System.out.println("12&3 : [^#&@]* = " + "12&3".matches("[^#&@]*"));
        System.out.println("12@3 : [^#&@]* = " + "12@3".matches("[^#&@]*"));
        System.out.println("12-3 : [^#&@]* = " + "12-3".matches("[^#&@]*"));
        System.out.println("----------------------");

        String str2 = "18";
        System.out.println(str2.matches("^(0\\d{1}|1\\d{1}|2[0-3])"));

    }

    public static void fillAllStringField(final Object dto, final String defaultValue) {
        if (null == dto) {
            return;
        }
        final Field[] fields = dto.getClass().getDeclaredFields();
        for (Field aField : fields) {
            Class<?> types = aField.getType();
            if (String.class.equals(types)) {
                boolean accessible = aField.isAccessible();
                aField.setAccessible(true);
                try {
                    final String tempString = (String) aField.get(dto);
                    if (tempString == null) {
                        aField.set(dto, defaultValue);
                    }
                } catch (IllegalArgumentException e) { // e.printStackTrace();
                } catch (IllegalAccessException e) { // e.printStackTrace();
                }
                aField.setAccessible(accessible);
            }
        }
    }

    public static void trimAllStringTypeVars(final Object dto) {
        if (null == dto) {
            return;
        }
        final Field[] fields = dto.getClass().getDeclaredFields();
        for (Field aField : fields) {
            Class<?> types = aField.getType();
            if (String.class.equals(types)) {
                boolean accessible = aField.isAccessible();
                aField.setAccessible(true);
                try {
                    final String tempString = (String) aField.get(dto);
                    if (tempString != null) {
                        aField.set(dto, tempString.trim());
                    }
                } catch (IllegalArgumentException e) { // e.printStackTrace();
                } catch (IllegalAccessException e) { // e.printStackTrace();
                }
                aField.setAccessible(accessible);
            }
        }
    }

    /** 正數. */
    public static void positiveNumber() {
        String str = "123.23";
        System.out.println(str.matches("[0-9]+[\\..][0-9]+"));

        String str2 = "-123.456";
        System.out.println(str2.matches("[-][0-9]+[\\..][0-9]+"));

        String str3 = "123.456";
        System.out.println(str3.matches("^[(-)|(\\.)][0-9]+[\\..][0-9]+")); // XXX
    }
}
