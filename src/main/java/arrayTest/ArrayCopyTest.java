package arrayTest;

import java.util.ArrayList;
import java.util.List;

public class ArrayCopyTest {
    //  System.arraycopy(src, srcPos, dest, destPos, length)
    //    SRC - 源數組。
    //    srcPos - 源數組中的起始位置。
    //    目標 - 目標數組。
    //    的destPos - x1達成：數據中的起始位置。
    //    長度 - 要複製的數組元素的數量。

    public static void main(String[] arg) {
        // List<Object> list = Arrays.asList(mergeArray(new Object()));
        // List<Object> list = Arrays.asList(mergeArray(new Object(), new Object()));
        // for (Object o : list) {
        //     System.out.println("o = " + o);
        // }

//         arrayCopyTest();
//        listCopyTest();
        arrayCopyTest2();
        
//        testDescAry();
    }

    private static Object[] mergeArray(Object x0, Object... xs) {

        if (xs == null || xs.length == 0) {
            System.out.println("[]Trace note 1");

            return new Object[] { x0 };

        } else {
            System.out.println("[]Trace note 2");

            Object[] copyOf = new Object[xs.length + 1];
            copyOf[0] = x0;
            System.arraycopy(xs, 0, copyOf, 1, xs.length);
            return copyOf;
        }

    }

    private static void listCopyTest() {
        List<String> strList = new ArrayList<String>();
        strList.add("A");
        strList.add("B");
        strList.add("C");

        for (String s : strList) {
            System.out.println("s1 = " + s);
        }

        List<String> destList = new ArrayList<String>();
        destList.add("1");
        destList.add("2");
        destList.add("3");

        System.arraycopy(strList, 0, destList, 1, strList.size()); // fail!!!
        for (String s : destList) {
            System.out.println("s2 = " + s);
        }
        
    }

    private static void arrayCopyTest() {
        String[] strList = { "A", "B", "C" };

        for (String s : strList) {
            System.out.println("s1 = " + s);
        }
        System.out.println("------------");

        String[] destList = new String[strList.length + 1];

        System.arraycopy(strList, 0, destList, 1, strList.length);
        for (String s : destList) {
            System.out.println("s2 = " + s);
        }

    }
    
    private static void arrayCopyTest2() {
        List<String> strList = new ArrayList<String>();
        strList.add("A");
        strList.add("B");
        strList.add("C");
        
        String[] strArray = strList.toArray(new String[]{});
        
        for (String s : strArray) {
            System.out.println("s1 = " + s);
        }
        System.out.println("------------");
        
        String[] destList = new String[strList.size() + 1];
        destList[0] = "U";
        
        System.arraycopy(strArray, 0, destList, 1, strList.size());
        for (String s : destList) {
            System.out.println("s2 = " + s);
        }
    }
    
    private static void testDescAry() {
        String[] strList = { "A", "B", "C" };
        
        StringBuilder orderStr = new StringBuilder();
        for (int i = strList.length; i > 0; i--) {
            System.out.println(i + ": " + strList[i - 1]);
            
            orderStr.append(strList[i - 1]);
            orderStr.append(", ");
        }
        
        System.out.println(orderStr);
    }

}
