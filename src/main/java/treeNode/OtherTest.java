package treeNode;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class OtherTest {
    public static void main(String[] args) {
        String checkOrder = "1001,001,002";
        
       System.out.println(StringUtils.contains("1001,001", checkOrder));

//        String[] array = checkOrder.split(",");
//        System.out.println(array.length);
//        System.out.println(array[array.length-1]);
//
//        for (String s : array) {
//            System.out.println("s1 = " + s);
//        }
//        System.out.println("------------");
//
//        String[] destList = new String[array.length - 1];
//
//        System.arraycopy(array, 0, destList, 0, array.length - 1);
//        for (String s : destList) {
//            System.out.println("s2 = " + s);
//        }
//
//        System.out.println(StringUtils.join(destList, ","));
    }
}
