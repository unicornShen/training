package comparisonBO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ComparisonBO {
    public static void main(String[] args) {
        Unicorn u = new Unicorn();
        Kevin l = new Kevin();
        //		comparisonBo(u, l);
        //l = (Leo) turnHsToMain2(u, l);
        //System.out.println("l.getName() = " + l.getName());
        //System.out.println("u.getName() = " + u.getName());

        CompareHelper.comparisonBO(u, l);
    }

    public static void comparisonBo(Object o1, Object o2) {
        Field[] field = o1.getClass().getDeclaredFields();
        field[0].getAnnotations();
        field[0].getClass();
        field[0].getDeclaredAnnotations();
        field[0].getDeclaringClass();
        field[0].getGenericType();
        field[0].getModifiers();
        field[0].getName();
        field[0].getType();

        System.out.println("getAnnotations=" + field[0].getAnnotations());
        System.out.println("getClass" + field[0].getClass());
        System.out.println("getDeclaredAnnotations=" + field[0].getDeclaredAnnotations());
        System.out.println("getDeclaringClass=" + field[0].getDeclaringClass());
        System.out.println("getGenericType=" + field[0].getGenericType());
        System.out.println("getModifiers=" + field[0].getModifiers());
        System.out.println("getName=" + field[0].getName());
        System.out.println("getType=" + field[0].getType());
    }

    private static Object turnHsToMain(Object hs, Object main) {
        Field[] fieldList = main.getClass().getDeclaredFields();
        System.out.println("fieldList = " + fieldList.length);
        for (Field f : fieldList) {
            try {
                Method getMethod = hs.getClass().getMethod(genMethodName(f.getName(), "get"), null);
                System.out.println("f.getType() = " + f.getClass().getInterfaces());
                Method setMethod = main.getClass().getMethod(genMethodName(f.getName(), "set"), f.getType());
                System.out.println("setMethod.getName() = " + setMethod.getName());
                setMethod.invoke(main, getMethod.invoke(hs, null));

            } catch (Exception e) {
                // 這裡可能會遇到找不到method的狀況，所以發生Exception為正常現象
                e.printStackTrace();
            }
        }
        return main;// 其實也可以不用傳
    }

    private static String genMethodName(String fieldName, String priorName) {
        String methodName = "";
        String firstStr = fieldName.substring(0, 1);
        methodName = priorName + firstStr.toUpperCase() + fieldName.substring(1);
        return methodName;
    }

    private static Object turnHsToMain2(Object hs, Object main) {
        Method[] method = main.getClass().getMethods();
        String methodName = "";
        for (Method m : method) {
            try {
                methodName = m.getName();
                System.out.println("m.getName() = " + m.getName());

            } catch (Exception e) {
                // 這裡可能會遇到找不到method的狀況，所以發生Exception為正常現象
                // e.printStackTrace();
            }
        }
        return main;
    }

}
