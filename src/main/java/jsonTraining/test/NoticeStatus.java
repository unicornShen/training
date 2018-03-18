package jsonTraining.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * The Enum NoticeStatus.
 * 
 * 9001 : N -> (S/E) -> (J) -> (F/X) 9001 : N -> (S/E) -> (F/X)
 * 
 * 9002 : N -> (S/J) -> (X)
 * 
 */
public enum NoticeStatus {

    //@formatter:off
    None     ("N", "{9916:'尚未指派　', 9001:'發送前　　　　　', 9002:'新建資料（接收／尚未處理）'}"), // 未通報 or 未執行.   | 9001: 發送前                      | 9002: 新建資料(接收/尚未處理)
    Success  ("S", "{9916:'指派中　　', 9001:'發送成功　　　　', 9002:'執行完成　　　　　　　　　'}"), //　通報成功.        | 9001: 發送成功                    | 9002: 執行完成
    Error    ("E", "{9916:'指派失敗　', 9001:'發送失敗　　　　', 9002:'執行失敗　　　　　　　　　'}"), //　通報失敗.        | 9001: 發送失敗                    | 9002: 執行失敗
    Queue    ("Q", "{9916:'作業排程中', 9001:'作業排程中　　　', 9002:'作業排程中　　　　　　　　'}"), //
    Job      ("J", "{9916:'作業開始　', 9001:'作業執行中　　　', 9002:'設定指派ＪＯＢ　　　　　　'}"), //　指派作業.       | 9001:             | 9002: 設定指派JOB
    Finish   ("F", "{9916:'作業完成　', 9001:'作業完成　　　　', 9002:'作業完成　　　　　　　　　'}"), //　即時更新成功.     | 9001:  指派JOB執行完成 / 作業完成
    eXception("X", "{9916:'作業失敗　', 9001:'遠端回報執行異常', 9002:'執行異常　　　　　　　　　'}"), //　即時更新失敗.     | 9001:  遠端回報執行例外   | 9002: 執行例外
    Resent   ("R", "{9916:'　　　　　', 9001:'重送　　　　　　', 9002:'　　　　　　　　　　　　　'}"), //
    //@formatter:on
    ;
    
    final private static Map<String, NoticeStatus> MAP;

    final private String code;

    final private String text9916;

    final private String text9001;

    final private String text9002;

    static {
        Map<String, NoticeStatus> map = new HashMap<String, NoticeStatus>();
        for (NoticeStatus noticeStatus : values()) {
            map.put(noticeStatus.getCode(), noticeStatus);
        }
        MAP = Collections.unmodifiableMap(map);
    }

    /**
     * @return the text9001
     */
    public String getText9001() {
        return this.text9001;
    }

    /**
     * @return the text9002
     */
    public String getText9002() {
        return this.text9002;
    }

    /**
     * @return the text9916
     */
    public String getText9916() {
        return this.text9916;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
    * 
    */
    private NoticeStatus(String code, String texts) {
        this.code = code;
        JSONObject json = (JSONObject) JSONSerializer.toJSON(texts);
        this.text9916 = initValue(json, "9916");
        this.text9001 = initValue(json, "9001");
        this.text9002 = initValue(json, "9002");
    }

    public static NoticeStatus lookup(String code) {
        return MAP.get(code);
    }

    final private String initValue(JSONObject json, final String key) {
        if (json.containsKey(key)) {
            return json.getString(key);
        }
        return this.name();
    }

    public static void main(String[] args) {
        System.out.println(Finish);
        System.out.println(Finish.getText9001());
        System.out.println(Finish.getText9002());
        System.out.println(Finish.getText9916());
    }
}