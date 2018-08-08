package generateCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenerateMappingCode {
    public static void main(String[] args) {
        //        genMappingCode(TurnTool.upFirstCharByStr("_"));

        genSetMappingCode(baseData());
        genGetMappingCode(baseData());

    }

    /**
     * 產出後沒註解
     */
    public static void genMappingCode(List<String> list) {
        List<String> returnList = new ArrayList<String>();
        StringBuilder reStr = null;

        for (String str : list) {
            reStr = new StringBuilder();
            reStr.append("type.set");
            reStr.append(TurnTool.upFirstStr(str));
            reStr.append("(this.");
            reStr.append(str);
            reStr.append(");");

            System.out.println(reStr.toString());
            returnList.add(reStr.toString());
        }
    }

    /**
     * 產出後有註解 
     */
    static void genSetMappingCode(Map<String, String> map) {
        List<String> returnList = new ArrayList<String>();
        StringBuilder reStr = null;

        // 
        for (String key : map.keySet()) {
            reStr = new StringBuilder();
            reStr.append("type.set");
            reStr.append(TurnTool.upFirstStr(key));
            reStr.append("(rs.getString(\"");
            reStr.append(key);
            reStr.append("\")); // ");
            reStr.append(map.get(key));

            System.out.println(reStr.toString());
            returnList.add(reStr.toString());
        }

    }

    /**
     * 產出後有註解 
     */
    static void genSetMappingCode_o(Map<String, String> map) {
        List<String> returnList = new ArrayList<String>();
        StringBuilder reStr = null;

        for (String key : map.keySet()) {
            reStr = new StringBuilder();
            reStr.append("type.set");
            reStr.append(TurnTool.upFirstStr(key));
            reStr.append("(this.");
            reStr.append(key);
            reStr.append("); // ");
            reStr.append(map.get(key));

            System.out.println(reStr.toString());
            returnList.add(reStr.toString());
        }

    }

    static void genGetMappingCode(Map<String, String> map) {

        List<String> returnList = new ArrayList<String>();
        StringBuilder reStr = null;

        for (String key : map.keySet()) {
            reStr = new StringBuilder();
            reStr.append("content.append(");
            reStr.append("tcde005w.get");
            reStr.append(TurnTool.upFirstStr(key));
            reStr.append("()");
            reStr.append("); // ");
            reStr.append(map.get(key));

            System.out.println(reStr.toString());
            returnList.add(reStr.toString());
        }

    }

    private static Map<String, String> baseData() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("areaCode", "行政區域");
        map.put("personId", "統號");
        map.put("personName", "姓名");
        map.put("birthYyymmdd", "出生年月日");
        map.put("deptPermit", "原核准出國機關");
        map.put("txApprovalYyymmdd", "原核准出國日期");
        map.put("fromto", "原核准出國前往地點");
        map.put("permit", "原核准出國依據條款");
        map.put("txApprovalDocu", "原核准出國文號(字)");
        map.put("txApprovalNo", "原核准出國文號(號)");
        map.put("foreign", "僑民之僑居國別");
        map.put("reason", "原核准出國是由");

        return map;

    }

    /**
     * 
     */
    public static void genSetThisCode(Map<String, String> map) {
        StringBuilder reStr = null;

        for (String key : map.keySet()) {
            reStr = new StringBuilder();
            reStr.append("");
            reStr.append("this.");
            reStr.append(key);
            reStr.append(" = type.get");
            reStr.append(TurnTool.upFirstStr(key));
            reStr.append("(); // ");
            reStr.append(map.get(key));

            System.out.println(reStr.toString());
        }

    }
}
