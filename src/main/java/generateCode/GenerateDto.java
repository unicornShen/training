/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package generateCode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GenerateDto {

    public static void main(String[] args) {

        for (String str : baseData().keySet()) {
            System.out.println(addTitle(str, baseData().get(str)));
        }

        allField(baseData().keySet());

    }

    //------------------------------
    //---- for CTBC.
    //------------------------------

    /** for CTBC. */
    public static void genDtoForCTBC(Map<String, String> baseMap) {
        for (String str : baseMap.keySet()) {
            System.out.println(addTitleForCTBC(str, baseMap.get(str)));
        }
    }
    
    private static String addTitleForCTBC(String fieldName, String comment) {
        StringBuilder reStr = new StringBuilder();

        reStr.append("/** " + comment + ". */");
        reStr.append("\n");
//        reStr.append("@TableColumnName(\"" + fieldName.toLowerCase() + "\")");
//        reStr.append("\n");
        reStr.append("@CsvColumn");
        reStr.append("\n");
        reStr.append("private String " + GenerateAll.upperFirstCharByStr(fieldName.toLowerCase(), "_") + ";");
        reStr.append("\n");

        return reStr.toString();
    }
    
    //------------------------------
    //---- 
    //------------------------------

    public static void genDto(Map<String, String> baseMap) {
        for (String str : baseMap.keySet()) {
            System.out.println(addTitle(str, baseMap.get(str)));
        }
    }
    
    private static String addTitle(String fieldName, String comment) {
        StringBuilder reStr = new StringBuilder();

        // reStr.append("@XmlElement(name = \"" + upFirstChar(fieldName) + "\")");
        // reStr.append("\n");
        // reStr.append("@FieldName(\"" + comment + "\")");
        // reStr.append("\n");
        reStr.append("/** " + comment + ". */");
        reStr.append("\n");
        reStr.append("private String " + fieldName + ";");
        reStr.append("\n");

        return reStr.toString();
    }

    private static String upFirstChar(String inputStr) {
        inputStr = inputStr.substring(0, 1).toUpperCase() + inputStr.substring(1);
        return inputStr;
    }

    private static void allField(Set<String> keySet) {
        StringBuilder str = new StringBuilder();

        for (String key : keySet) {
            str.append("\"");
            str.append(key);
            str.append("\", ");
        }

        System.out.println(str.toString());

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

    //================================================
    //== [Enumeration types] Block Start
    //====
    //====
    //== [Enumeration types] Block End 
    //================================================
    //== [static variables] Block Start
    //====
    //====
    //== [static variables] Block Stop 
    //================================================
    //== [instance variables] Block Start
    //====
    //====
    //== [instance variables] Block Stop 
    //================================================
    //== [static Constructor] Block Start
    //====
    //====
    //== [static Constructor] Block Stop 
    //================================================
    //== [Constructors] Block Start (含init method)
    //====
    //====
    //== [Constructors] Block Stop 
    //================================================
    //== [Static Method] Block Start
    //====
    //====
    //== [Static Method] Block Stop 
    //================================================
    //== [Accessor] Block Start
    //====
    //====
    //== [Accessor] Block Stop 
    //================================================
    //== [Override Method] Block Start
    //====
    //====
    //== [Override Method] Block Stop 
    //================================================
    //== [Method] Block Start
    //====
    //####################################################################
    //## [Method] sub-block : 
    //####################################################################
    //====
    //== [Method] Block Stop 
    //================================================
}
