package enumTraining.phase1;

import enumTraining.phase1.EnumBasic.Template1;
import enumTraining.phase1.EnumBasic.Template3;
import enumTraining.phase1.EnumBasic.Template4;
import enumTraining.phase1.EnumBasic.Template5;

public class EnumBasicTest {

    public static void main(String[] args) {
        test1();
        test2();
        test5();

    }

    /***/
    public static void test1() {
        // Test1
        for (Template1 e : EnumBasic.Template1.values()) {
            // 這是基本的測試，實戰中比較少看到的用法...
            System.out.println("名稱:" + e.name() + " 序號:" + e.ordinal());
        }

        // 比較方式，兩種都可以。
        System.out.println(Template1.A == Template1.B);
        System.out.println(Template1.A.equals(Template1.B));
    }

    /***/
    public static void test2() {
        for (Template3 e : Template3.values()) {
            System.out.println("取用enumeration裡的field值 = " + e.getName());
        }

        for (Template4 e : Template4.values()) {
            System.out.println(String.format("{%s}:{%s}", e.getName(), e.getCode()));
        }
    }

    /** 其他的可以自己測測看. */

    /***/
    public static void test5() {
        for (Template5 e : Template5.values()) {
            for (String str : e.getWorks()) {
                System.out.println(String.format("[%s]:[%s]", e.name(), str));
            }
        }
    }
}
