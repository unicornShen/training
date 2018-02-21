package enumTraining.phase3;

/**
 * 最終型態.
 * 
 * @author Unicorn
 */
public enum Operation3 {
    PLUS("+") {
        @Override
        int count(int number1, int number2) {
            return number1 + number2;
        }
    }, //
    MINUS("-") {
        @Override
        int count(int number1, int number2) {
            return number1 - number2;
        }
    }, //
    TIMES("*") {
        @Override
        int count(int number1, int number2) {
            return number1 * number2;
        }
    }, //
    DIVIDE("/") {
        @Override
        int count(int number1, int number2) {
            return number1 / number2;
        }
    }, //
    ;
    final private String symbol;

    private Operation3(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    //----------------------------------------------------
    //-- ※關鍵※
    //-- 在enum中先告一個abstract的method，
    //-- 所有的種類就都必須自行實做這個method， 
    //-- 如此一來就不必擔心預不存在的Operation，也不必擔心拋出例外的狀況了。
    //----------------------------------------------------
    abstract int count(int number1, int number2);

    /** Test function. */
    public static void main(String[] args) {
        // 只要知道用哪一種運算方式，直接呼叫count就OK了。
        System.out.println(Operation3.PLUS.count(6, 3));
    }

    //----------------------------------------------------
    //-- 我學到這裡的時候，覺得enum一整個很犯規，
    //-- 它不但可以取代過去的靜態變數，而且還可以帶參數，
    //-- 重點是它還是單一實例，
    //-- 到最後竟然還有內建interface的功能。
    //----------------------------------------------------
    
    //
    // 接下來phase4之後都是enum的相關應用。
    //
}
