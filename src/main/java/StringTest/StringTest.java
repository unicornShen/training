package StringTest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.StringSubstitutor;

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

        // testMatches();

        // positiveNumber();

        // Object object = 123;
        // System.out.println(ObjectUtils.identityToString(object));
        // System.out.println(ObjectUtils.toString(object));
        // System.out.println(ObjectUtils.defaultIfNull(object, "default Value"));

        // traceSubstring();
        // testIndexOf();
        // testPatternMatcher();

        // testSplitSeq();
        // testGenUrlParm();
        testReplaceParm1();
        // testReplaceParm2();
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

    public static void traceSubstring() {
        String str = "1234567890一二三四五六七八九零";
        System.out.println(str.subSequence(15, 16));
        System.out.println(str.subSequence(0, 15));
        System.out.println(str.subSequence(3, 7));
        System.out.println(str.subSequence(10, 15));
    }

    public static void testIndexOf() {
        String str = "asdfghjbBnmd";
        System.out.println(str.indexOf(92));
    }

    public static void testPatternMatcher() {
        String fullSpcName = "HK-HONGKONG";
        String[] checkSpcNames = fullSpcName.split("-");
        String checkSpcName_1 = checkSpcNames[0];
        String checkSpcName_2 = checkSpcNames[1];

        String patternStr = "(\\s|^)" + checkSpcName_1 + "(\\s|$)";
        Pattern pattern = Pattern.compile(patternStr);

        String engName = "213 HK 456";
        Matcher matcher_db = pattern.matcher(engName);
        if (matcher_db.find()) {
            String newEngName = matcher_db.replaceAll(" " + checkSpcName_2 + " ").trim();
            System.out.println(newEngName); // 213 HONGKONG 456
        }
    }

    public static void testSplitSeq() {
        String str = "0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3,011,111,211,311,022,122,222,322,033,133,233,333,044,144,244,344,055,155,255,355,066,166,266,366,077,177,277,377,";

        final List<String> list = new ArrayList<>();
        getSplitSeq(list, str);
        for (String ans : list) {
            System.out.println("ans = " + ans);
        }
    }

    /** 遞迴切割刪除SEQ清單；太長丟到 DB 去查詢會壞掉. */
    public static void getSplitSeq(List<String> list, String str) {
        int length = StringUtils.length(str);

        if (length > 100) {
            int last = StringUtils.lastIndexOf(str, ",", 100);

            String before = StringUtils.substring(str, 0, last);
            String after = StringUtils.substring(str, last);

            list.add(before);
            getSplitSeq(list, after);

        } else {
            list.add(str);
        }
    }

    /** 產 URL 後面的參數. */
    public static void testGenUrlParm() {
        final NoticeParm parm = new NoticeParm();
        parm.setCaseType("AP");
        parm.setCaseSeq(125L);

        // ?todoItemSeq=0000&caseSeq=0000&stageId=XXXX&caseType=XX&status=XXXX
        final StringBuilder sb = new StringBuilder();
        sb.append("?");

        for (Field f : FieldUtils.getAllFields(NoticeParm.class)) {
            try {
                final Object value = PropertyUtils.getProperty(parm, f.getName());
                if (value != null) {
                    sb.append("&");
                    sb.append(f.getName());
                    sb.append("=");
                    sb.append(value);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(StringUtils.replaceOnce(sb.toString(), "&", ""));
    }

    public static void testReplaceParm1() {
        String str = "Tran.Cert.-{remitNo}.pdf";
        String param = StringUtils.substringBetween(str, "{", "}");

        System.out.println("param = " + param);

        String ans = StringUtils.replace(str, "{" + param + "}", "XXX123456789");
        System.out.println("ans = " + ans);
    }

    // https://commons.apache.org/proper/commons-text/apidocs/org/apache/commons/text/StringSubstitutor.html
    public static void testReplaceParm2() {
        String str = "Tran.Cert.-${remitNo}.pdf";

        // Build map
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("remitNo", "CV12345679");

        // Build StringSubstitutor
        StringSubstitutor sub = new StringSubstitutor(valuesMap);

        // Replace
        String resolvedString = sub.replace(str);
        System.out.println("resolvedString = " + resolvedString);
    }
}
