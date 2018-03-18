package enumTraining.phase2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 情境：
 * 模擬一組[流程狀態]的下拉共用選單。
 * </pre>
 * 
 * @author Unicorn
 */
public class EnumUsage1 {

    //----------------------------------------------------
    //---- [一些設計上的盲點]
    //----   你應該還記得，如果畫面上的下拉選單都從DB撈出來產生的恐怖情景，
    //----   然後你去問SD可能會得到一個回答：『因為想讓下拉可以即時動態變更』，
    //----   這時候問題出現了，SD就把所有的下拉都搬到DB做!!!???
    //---- 
    //----   思考問題1、 一個畫面20個下拉，DB就撈20次???
    //----   思考問題2、 如果有兩個下拉，它們的差異只是裡面的一兩項，那DB裡變成會存兩組很像的清單???
    //----   思考問題3、 真的所有的下拉，都有必要即時動態變更嗎??? (通常需要這種的都是有另一個功能在做吧!!!)
    //----   思考問題4、 做業務邏輯的除錯時，從程式中無法馬上得知實際下拉清單的內容；如果這時有DB可以連就算了，阿如果沒得連呢???
    //----   思考問題5、 做連動時，KEY沒有標準、不固定;原本可能是01，那今天改個規格，改成001時那不就悲劇了，所有用到的一支一支改...這時候只好請祖宗十八代出來維護世界和平了= =+++
    //----   ...一直思考下去是想不完的，直接用範例來理解吧.
    //----------------------------------------------------

    /** 流程狀態. */
    public enum ProcessStatus {
        ALL("1", "全部"), //
        FINISH("2", "已處理"), //
        UNFINISHED("3", "未處理"), //
        PROCESSING("4", "處理中"), //
        ;

        /**
         * 等同下拉選單的 value.
         * <pre>
         * 在此的參數(code)只是未來要找相對應的Enum用，
         * 切記:不是拿來比對用的；比對方式請看EnumUsage1Test.java
         * 也可以當作是排序的index.
         * <pre>
         */
        private final String code;

        /** 等同下拉選單的 label. */
        private final String name;

        private ProcessStatus(final String code, final String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        //----------------------------------------------------
        //---- 轉換成UI可以使用的List.
        //----------------------------------------------------

        /** 靜態變數. */
        private static List<MenuVo> ITEMS;

        
        /**
         * ------------------------------------
         * -- 請先看完 static block裡的東西，再回來看說明。
         * ------------------------------------
         * 
         * Collections.unmodifiable 這傢伙...我猜你應該是第一次看到吧?
         * 無所謂，誰沒有第一次!!!
         * 
         * 如同method名稱，他會回傳一個無法被修改的Collection，為什麼要這樣做呢?
         * 在這邊定義好的東西可能會很多人使用，
         * 但是如果你沒加上這段，搞不好有人就會不小心對這個已經定義好內容的Collection做修改，
         * 還記得enum是單一實例吧!
         * 接下來會發生什麼事，相信你應該已經明白了。
         * 
         */
        static { // static block 不用多解釋了吧.
            final List<MenuVo> items = new ArrayList<MenuVo>();
            for (ProcessStatus ps : ProcessStatus.values()) {
                MenuVo mv = new MenuVo(ps.getCode(), ps.getName());
                items.add(mv);
            }
            
            ITEMS = Collections.unmodifiableList(items);
        }

        /**
         * <b>將Enum轉換成List.</b>
         * <p>
         * 這邊的 MenuVo 是顯示下拉的物件，在ZK好像叫 listItme?? 不記得了。
         */
        public static List<MenuVo> toList() {
            return ITEMS;
        }
        
        //----------------------------------------------------
        //---- 建立對應MAP.
        //----------------------------------------------------

        /** 靜態變數. */
        private static Map<String, ProcessStatus> MAP;
        
        /** 這邊為了分區講解，所以分開寫，實作上是和上面那一個static block合再一起寫的. */
        static {
            final Map<String, ProcessStatus> map = new HashMap<String, ProcessStatus>();
            for (ProcessStatus ps : ProcessStatus.values()) {
                map.put(ps.getCode(), ps);
            }
            MAP = Collections.unmodifiableMap(map);
        }
        
        /**
         * <b>找出對應的Enum type.</b>
         * <p>
         * 以Enum 裡的 code 當KEY，
         * 用在已知選則項目時，需取回原本的Enum type做其他的事(比對之類的..)。
         * <p>
         * 在這個情境下，目前可以做的事大概只有取得中文。
         */
        public static ProcessStatus lookup(final String code) {
            return MAP.get(code);
        }

    }
}
