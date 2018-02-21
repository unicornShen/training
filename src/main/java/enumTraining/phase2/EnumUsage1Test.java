package enumTraining.phase2;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import enumTraining.phase2.EnumUsage1.ProcessStatus;

public class EnumUsage1Test {
    public static void main(String[] s) {
        generateSelectionItem();
        doSomething();
        doCompare();
        refineSource();
    }

    /***/
    private static void generateSelectionItem() {
        System.out.println("[U]generateSelectionItem()");

        List<MenuVo> menuList = ProcessStatus.toList(); // 從ProcessStatus這個 enum type取出先前做好的list.
        for (MenuVo vo : menuList) {
            System.out.println(String.format("value=[%s], label=[%s]", vo.getMenuValue(), vo.getMenuLabel()));
            //System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.MULTI_LINE_STYLE));
        }
    }

    /***/
    private static void doSomething() {
        System.out.println("[U]doSomething()");

        ProcessStatus status = ProcessStatus.lookup("1");// UI上選到"全部"之後，從selectedItem.getVaule之類的方法拿回它的值，接著就可以找回原來的 enum type.
        System.out.println("labelName = " + status.getName());
        // System.out.println(ToStringBuilder.reflectionToString(status, ToStringStyle.MULTI_LINE_STYLE));
    }

    /** enum 的比對. */
    private static void doCompare() {
        System.out.println("[U]doCompare()");

        // 兩種比對方式沒什麼差，看個人習慣使用...(複習一下，EnumBasicTest.java 中有提到。)
        System.out.println(ProcessStatus.PROCESSING == ProcessStatus.ALL);
        System.out.println(ProcessStatus.FINISH.equals(ProcessStatus.FINISH));
    }

    /** 內鍵一個找出原本 Enum 的方式. */
    private static void refineSource() {
        System.out.println("[U]refineSource()");

        // 傳入的字串是 ProcessStatus.PROCESSING.name() <--這是內建的方法 ；取得Enum命名的名稱。
        ProcessStatus ps = ProcessStatus.valueOf("PROCESSING");

        System.out.println(ToStringBuilder.reflectionToString(ps, ToStringStyle.MULTI_LINE_STYLE));
    }
}
