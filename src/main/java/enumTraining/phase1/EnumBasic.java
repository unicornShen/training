package enumTraining.phase1;

/**
 * <pre>
 * Enumeration 可以說是靜態變數(private final static String XXXX)的進階版，
 * 多半在Re-factoring階段我都會把靜態變數換掉，
 * 不，應該是說，在開發階段我已經不再使用常數變數這個東西了，
 * 但是還是有使用的情境，這個靜態變數只使用於當下的class中，最常看到的應該就是log4j了，什麼?不懂?
 * 好吧...我知道原因，其實log4j的宣告應該像：
 * <code>
 *  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(className.class);
 * </code>
 * 偏離主題了...
 * 
 * 在此介紹  Enumeration 在 class 中使用，當然他也可以單獨存在。
 * 實做中，通常我會這樣做...
 * 用class或interface來分類enumeration，
 * 以MiTac的專案來說，我可能會建 DepEnum 的 class，然後裡面放DEP相關的 enumeration。
 *   
 *   
 * ※補充說明：
 *   每一個enumeration都可以是一個獨立的個體，
 *   如果是常數變數，它就必須依賴在物件底下，
 *   講明白一點就是使用常數的時候，它必須依賴一個實體，
 *   越多地方使用，這傢伙的造成的浪費就越多。
 *   (這個說明是我個人理解的，JDK實做是如何沒去研究，不過常數變數會造成的問題是確定的。)
 *   
 * 
 * 在Effective java書中有提到：
 *   JDK 1.5以後，請用Enum代替常數變數(item 30)。
 *   Enum 是單一實例的(item 3)。
 * 
 * 
 * 有空可以看看Effective java這本書。
 * 
 * </pre>
 *
 * @author Unicorn
 */
public interface EnumBasic {
    //####################################################################
    //## [Enumeration] sub-block : 一般人對Enumeration的了解
    //####################################################################

    /** 
     * 最最基本的模式.<br>
     * 修飾子用法同class，不多做解釋了。
     */
    public enum Template1 {
        A, B, C, D; // enumeration type : 通常是大寫宣告，
    }

    /** 
     * [[獨家分享]]<br>
     * 稍微介紹一下 Eclipse 編輯實的技巧：<br>
     * 在同一Block的code最後加上 "//" ，<br>
     * 如此一來在 control + shift + F 時，格式不會跑掉，<br>
     * 如下.....
     */
    enum Template2 {
        TYPE_A, //
        TYPE_B, //
        TYPE_C, //
        TYPE_D, // 在最後一個元素後面加上逗點也不會錯。(這個設計讚)
        ; // 結束

        // 實用的編輯技巧，提升程式閱讀性。
    }

    //####################################################################
    //## [Enumeration] sub-block : 真正Enumeration的用法(簡單版)
    //####################################################################

    /**
     * Enumeration帶參數(可以看成是建構子)。
     */
    enum Template3 {
        TYPE_A("name1"), //
        TYPE_B("name2"), //
        TYPE_C("name3"), //
        ;

        /** 
         * 自訂的enumeration參數。 
         * 這邊有幾個要注意的重點，
         * 1.修飾子的用法同field，通常我會加private然後給他getter。
         * 2.加入final代表，enumeration type後面一定要有的參數，沒有的話eclipse會提示你加一個constructor。
         */
        private final String name;

        /** Enumeration constructor. */
        private Template3(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

    }

    /**
     * 多constructor.
     */
    enum Template4 {
        TYPE_A("name1", "code1"), // 後面帶參數的順序是依照constructor傳入的參數.
        TYPE_B("name2"), //
        TYPE_C("name3", "code3"), //
        TYPE_D("name4"), //
        ;

        /** Declare 兩個 field 比較一下。 */
        private final String name;

        private String code;

        /** Enumeration constructor. */
        private Template4(String name, String code) {
            this.name = name;
            this.code = code;
        }

        /** Enumeration constructor. */
        private Template4(String name) {
            this.name = name;
        }

        public String getCode() {
            return this.code;
        }

        // 基本上，定義好的參數通常是不准修改的，所以大多都會加final，視情況而定。
//        public void setCode(String code) {
//            this.code = code;
//        }

        public String getName() {
            return this.name;
        }

    }
    
    /**
     * 想怎麼用都可以...
     */
    enum Template5{
        TYPE_A("1", "2"), //
        TYPE_B("3"), //
        TYPE_C("4", "5", "6"), //
        TYPE_D, //
        ;
        
        private final String[] works;

        /** 
         * Enumeration constructor. 
         * 
         * 這邊用點點點的方式讓參數傳入。
         */
        private Template5(String... works) {
            this.works = works;
        }

        public String[] getWorks() {
            return this.works;
        }
        
    }
    

}
