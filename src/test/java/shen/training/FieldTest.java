package shen.training;

import java.lang.reflect.Field;

import comparisonBO.Unicorn;

/**
 *
 */
public class FieldTest {

    public static void main(String[] args) {
        Unicorn u = new Unicorn();

        test(Unicorn.class);

    }

    public static void test(Class<?> cls) {
        for (Field f : cls.getDeclaredFields()) {
            System.out.println(f.getDeclaringClass());
        }
    }
}
