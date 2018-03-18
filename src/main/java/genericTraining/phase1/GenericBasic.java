package genericTraining.phase1;

import genericTraining.vo.IVo;
import genericTraining.vo.UnicornVo;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 泛型基礎篇。
 *
 * @author Unicorn
 */
public class GenericBasic {

    //####################################################################
    //## [Method] sub-block : 使用[指定類別]傳入。
    //####################################################################

    /**
     * 最常見的用法。
     */
    public void test0(List<UnicornVo> list) {
        System.out.println("test0");

        for (UnicornVo vo : list) {
            for (Field field : vo.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    //####################################################################
    //## [Method] sub-block : 使用[任意類別]傳入。
    //####################################################################

    /**
     * 一般用法。
     */
    public void test1_1(List<?> list) {
        System.out.println("test1");

        for (Object object : list) {
            for (Field field : object.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    /**
     * 指定[繼承]關係的用法。
     */
    public void test1_2(List<? extends Object> list) {
        System.out.println("test1_2");

        for (Object object : list) {
            for (Field field : object.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    /**
     * 指定[介面]關係的用法，在實戰中常用。
     */
    public void test1_3(List<? extends IVo> list) {
        System.out.println("test1_3");

        // 可以用interface解析.
        for (IVo ivo : list) {
            for (Field field : ivo.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }
    
    //####################################################################
    //## [Method] sub-block : 使用[泛型]傳入。
    //####################################################################

    /**
     * 在Method中使用泛型 ，須在void(回傳型態)的前一個位置中,加入類似宣告泛型的語法。
     */
    public <T> void test2_1(List<T> list) {
        System.out.println("test2_1");

        for (T t : list) {
            for (Field field : t.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    /**
     * 可以宣告多個，名稱可以隨意取，通常習慣是使用大寫，用法如下。
     */
    public <R, U> void test2_2(List<R> list, U genericName) {
        System.out.println("test2_2");
        System.out.println("genericName = " + genericName);

        for (R r : list) {
            for (Field field : r.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    /**
     * 使用泛型指定[繼承]關係的用法。
     */
    public <T extends Object> void test2_3(List<T> list) {
        System.out.println("test2_3");

        for (T t : list) {
            for (Field field : t.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    /**
     * 使用泛型指定[介面]關係的用法。
     */
    public <T extends IVo> void test2_4(List<T> list) {
        System.out.println("test2_4");

        for (IVo ivo : list) {
            for (Field field : ivo.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }
    
    /**
     * 懶得測自己來吧...
     */
    public <T> void test2_5(List<? extends T> list) {
        System.out.println("test2_5");
        
        for (T t : list) {
            for (Field field : t.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }
    
    /**
     * 懶得測自己來吧...
     */
    public <T extends IVo> void test2_6(List<? super T> list) {
        System.out.println("test2_6");
        
        for (Object obj : list) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                // 列出所有的屬性名稱。
                System.out.println("field.getName()=" + field.getName());
            }
        }
    }

    //####################################################################
    //## [Method] sub-block : Class 中使用[泛型]。
    //####################################################################

    // 在此用inner做說明.

    /**
     * 一般的class.
     */
    class GenericClass1 {

        /**
         * 若class沒用到泛型，class下的method就須宣告泛型.
         */
        public <T> void toDo(T variableName) {

        }
    }

    /**
     * Class中使用泛型.<br>
     * 通常使用於interface比較多. 
     */
    class GenericClass2<T> {

        /**
         * 若class有用到泛型，class下的method就不須宣告泛型.
         */
        public void toDo(T variableName) {

        }
    }

}
