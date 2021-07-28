package collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

public class CollectionTest3 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        final List<String> list = null;
        System.out.println(CollectionUtils.isNotEmpty(list));
    }

    private static void test2() {
        final List<String> list = new ArrayList<>();
        System.out.println(CollectionUtils.isNotEmpty(list));
    }

    private static void test3() {
        final List<String> list = ListUtils.EMPTY_LIST;
        System.out.println(CollectionUtils.isNotEmpty(list));
    }

}
