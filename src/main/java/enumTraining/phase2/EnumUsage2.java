package enumTraining.phase2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * 
 * 情境：下拉連動。
 * 
 * 模擬一組網頁分類的下拉選單。
 * (手邊沒現成的,隨便舉例.)
 * 
 * </pre>
 * 
 * @author Unicorn
 */
public class EnumUsage2 {

    /**
     * 網站的總類.
     * <p>
     * 範例主要再說明連動，其他小地方略過， 略過部分在最下面我有放，請參閱。
     */
    public enum WebsiteType {
        TYPE_SEARCH("01.搜尋引擎網站"), //
        TYPE_BANK("02.銀行網站"), //
        TYPE_HOUS("03.房產網站"), //
        ;
        final private String text;

        private WebsiteType(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }

    /**
     * 網站.
     */
    public enum WebsiteItem {
        ITEM_1(WebsiteType.TYPE_SEARCH, "01.Google"), //
        ITEM_2(WebsiteType.TYPE_SEARCH, "02.YAHOO"), //

        ITEM_3(WebsiteType.TYPE_BANK, "01.台灣銀行"), //
        ITEM_4(WebsiteType.TYPE_BANK, "02.台北富邦"), //
        ITEM_5(WebsiteType.TYPE_BANK, "03.台新銀行"), //
        ITEM_6(WebsiteType.TYPE_BANK, "04.土地營行"), //

        ITEM_7(WebsiteType.TYPE_HOUS, "01.591"), //
        ITEM_8(WebsiteType.TYPE_HOUS, "02.信義房屋"), //
        ITEM_9(WebsiteType.TYPE_HOUS, "03.永慶房屋"), //
        ;

        final private WebsiteType type;

        final private String text;

        private WebsiteItem(WebsiteType type, String text) {
            this.type = type;
            this.text = text;
        }

        public WebsiteType getType() {
            return this.type;
        }

        public String getText() {
            return this.text;
        }

        public static Map<String, Map<String, String>> MENU;
        static {
            final Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
            for (WebsiteItem item : WebsiteItem.values()) {

                String typeMenuKey = item.type.name();
                if (!map.containsKey(typeMenuKey)) {
                    map.put(typeMenuKey, new TreeMap<String, String>()); // 這邊用TreeMap是靠慮到可能有排序問題。
                }
                map.get(typeMenuKey).put(item.name(), item.getText()); // 誰key 誰value 事情況而定。
            }

            MENU = Collections.unmodifiableMap(map);
        }

    }

    //----------------------------------------------
    //---- 補完
    //----------------------------------------------

    /** 網站的總類. */
    public enum websiteTypeFake {
        T1("01", "搜尋引擎網站"), // 
        T2("02", "銀行網站"), //
        T3("03", "購物網站"), //
        ;
        final private String code;

        final private String name;

        private websiteTypeFake(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        /**
         * 取回顯示的文字.(01.搜尋引擎網站)
         */
        public String getText() {
            StringBuilder text = new StringBuilder();
            text.append(this.getCode());
            text.append(".");
            text.append(this.getName());
            return text.toString();
        }

        // 略...
    }
}
