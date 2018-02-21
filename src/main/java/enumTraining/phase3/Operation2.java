package enumTraining.phase3;

/**
 * 稍微有經驗的enum做法.
 * 
 * @author Unicorn
 */
public enum Operation2 {
    PLUS("+"), //
    MINUS("-"), //
    TIMES("*"), //
    DIVIDE("/"), //
    ;
    final private String symbol;

    private Operation2(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    /** Test function. */
    public static void main(String[] args) {
        int value1 = 6;
        int value2 = 3;
        
        //---------------------------------
        //---- 以這個範例來說:
        //---- 稍微有經驗的人，會想到為了印出Log加上symbol的屬性。
        //---------------------------------
        System.out.println(String.format("%s %s %s = %s", value1, Operation2.PLUS.getSymbol(), value2, count(value1, value2, Operation2.PLUS)));
    }

    /**
     * 依照傳進來的運算子做計算.
     */
    public static int count(int number1, int number2, Operation2 op) {

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

        throw new AssertionError("Unknow OP:" + op);
    }
    
    //--------------------------------------------
    //---- 在count這個method中用了switch case來判斷目前要用哪一種運算方式，
    //---- 但是如果照這樣寫，是必會多出一個共用的method或class去處理判斷的部分，
    //---- 而且使用者還要承受拋出Exception後的處理成本，
    //---- 於是...enum的最終型態出現了!! 
    //---- (請看下一個...)
    //--------------------------------------------
}
