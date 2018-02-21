package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTest2 {
    public static void main(String[] s) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = Collections.emptyList();
        List<String> list3 = Collections.EMPTY_LIST;
        List<String> list4 = null;

        System.out.println("list1=" + list1.isEmpty());
        System.out.println("list2=" + list2.isEmpty());
        System.out.println("list3=" + list3.isEmpty());
        System.out.println("list4=" + list4.isEmpty());
    }
}
