/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package comparatorTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;

import comparisonBO.Unicorn;

public class ComparatorTest {
    public static void main(String[] args) {
        Unicorn u1 = new Unicorn();
        u1.setAge(11);
        u1.setName("u1");

        Unicorn u2 = new Unicorn();
        u2.setAge(33);
        u2.setName("");

        Unicorn u3 = new Unicorn();
        u3.setAge(22);
        u3.setName("u3");

        List<Unicorn> list = new ArrayList<Unicorn>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        sortTest(list);
    }

    private static void sortTest(final List<Unicorn> list) {
        // Comparator<Unicorn> com = new TryComparator();
        // Collections.sort(list, com);
        //
        // for (Unicorn u : list) {
        // System.out.println(u.getAge() + ":" + u.getName());
        // }

        // Comparator<Unicorn> com2 = new TryComparatorByName();
        // Collections.sort(list, com2);
        //
        // for (Unicorn u : list) {
        // System.out.println(u.getAge() + ":" + u.getName());
        // }

        Collections.sort(list, new TryComparatorByMulti());

        for (Unicorn u : list) {
            System.out.println(u.getAge() + ":" + u.getName());
        }
    }

    public static void testInnerClass(final List<Unicorn> list) {
        Collections.sort(list, new Comparator<Unicorn>() {
            @Override
            public int compare(Unicorn o1, Unicorn o2) {
                return new CompareToBuilder() //
                        .append(o1.getAge(), o2.getAge()) //
                        .toComparison();
            }
        });
    }

    /**
     * Sort class.
     */
    public static class TryComparator implements Comparator<Unicorn> {

        public int compare(Unicorn o1, Unicorn o2) {
            BigDecimal value1 = new BigDecimal(o1.getAge());
            BigDecimal value2 = new BigDecimal(o2.getAge());

            System.out.println(value1 + ":" + value2 + "=" + value2.compareTo(value1));

            return value2.compareTo(value1);
        }

    }

    /**
     * Sort class.
     */
    public static class TryComparatorByName implements Comparator<Unicorn> {

        public int compare(Unicorn o1, Unicorn o2) {
            System.out.println(o2.getName().compareTo(o1.getName()));
            return o2.getName().compareTo(o1.getName());
        }

    }

    /**
     * 多欄位排序.<br>
     * 越前面越優先.
     */
    public static class TryComparatorByMulti implements Comparator<Unicorn> {
        @Override
        public int compare(Unicorn o1, Unicorn o2) {
            return new CompareToBuilder() //
                    .append(o1.getAge(), o2.getAge()) //
                    .append(o1.getName(), o2.getName()) //
                    .toComparison();
        }
    }

}
