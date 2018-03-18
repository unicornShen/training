package enumTraining.phase3;

/**
 * 平凡人的enum做法.
 * 
 * @author Unicorn
 */
public enum Operation1 {
    PLUS, MINUS, TIMES, DIVIDE, // 平凡人對enum的理解可能者到這裡。 
    ;

    /** Test function. */
    public static void main(String[] args) {
        System.out.println("ans = " + count(6, 3, Operation1.PLUS));
    }

    /**
     * 依照傳進來的運算子做計算.
     */
    public static int count(int number1, int number2, Operation1 op) {

        //------------------------------------------
        //---- Seeing is believing... 
        //---- 你沒看錯，switch case是支援enum的!!!
        //----
        //-- 順帶一提:
        //-- 在Eclipse，如果在switch case中使用enum，
        //-- 他會幫你檢查是否所有的case都有列出，
        //-- 沒有的話會出現worry(黃色底線)，可以自己註解掉一組試看看。
        //------------------------------------------
        switch (op) {
            case PLUS:
                return number1 + number2;
            case MINUS:
                return number1 - number2;
            case TIMES:
                return number1 * number2;
            case DIVIDE:
                return number1 / number2;
        }

        // 傳進來的Operation不存在於switch case中。
        throw new AssertionError("Unknow OP:" + op);
    }
}
