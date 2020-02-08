/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package generateCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class GenerateAll {

    public static Map<String, String> baseData() {
        final Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("year_month", "年月");
        map.put("area_desc", "區域");
        map.put("branch_desc", "分行");
        map.put("ao_id", "理專ID");
        map.put("EMP_NAME", "理專");
        map.put("PERSON_ROLE", "FA等級");
        map.put("CUST_ID", "客戶ID");
        map.put("CUST_NAME", "客戶姓名");
        map.put("check_item", "查核項目");
        map.put("checkStatus", "案件狀態");
        map.put("CHECK_ITEM", "查核問題");
        map.put("QUESTVER", "版號");
        map.put("ANS", "查核結果");
        map.put("ANSTEXT", "其他註記");
        map.put("EMPNAME", "查核人");
        map.put("REPLYDATE", "查核日期");

        return map;
    }

    public static void main(String[] args) {
        // GenerateDto.genDto(turnKey(baseData()));
        // GenerateDto.genDtoForCTBC(baseData());
        // GenerateMappingCode.genSetMappingCode(turnKey(baseData()));
        // System.out.println("");
        // GenerateMappingCode.genSetThisCode(turnKey(baseData()));
        // System.out.println("");
        // GenerateMappingCode.genGetMappingCode(turnKey(baseData()));
        genList(baseData());
        System.out.println("");

        System.out.println("[DO]------------------------------------------------");
        GenerateCtbc.genDataObject(turnKey(baseData()));
        System.out.println("[Model]------------------------------------------------");
        GenerateCtbc.genModel(baseData());
        System.out.println("[DAO]------------------------------------------------");
        GenerateCtbc.genDao(baseData());
        System.out.println("------------------------------------------------");
    }

    public static Map<String, String> turnKey(Map<String, String> map) {
        Map<String, String> reMap = new LinkedHashMap<String, String>();

        for (String key : map.keySet()) {
            reMap.put(upperFirstCharByStr(key.toLowerCase(), "_"), map.get(key));
        }

        return reMap;
    }

    /**
     * <b>First char turn to upper</b>
     * <p>
     * 
     * @param inputStr Need turn string.
     * @param keyStr Turn main key.
     * @return
     */
    public static String upperFirstCharByStr(String inputStr, String keyStr) {
        StringBuilder sortOut = new StringBuilder();
        String temp = "";

        if (inputStr.indexOf(keyStr) != -1) {

            sortOut.append(inputStr.substring(0, inputStr.indexOf(keyStr)));
            sortOut.append(inputStr.substring(inputStr.indexOf(keyStr) + 1, inputStr.indexOf(keyStr) + 2).toUpperCase());
            sortOut.append(inputStr.substring(inputStr.indexOf(keyStr) + 2, inputStr.length()));

        } else {
            sortOut.append(inputStr);
        }

        if (sortOut.toString().indexOf(keyStr) != -1) {
            temp = upperFirstCharByStr(sortOut.toString(), keyStr);
        }

        if (StringUtils.isNotEmpty(temp)) {
            return temp;
        }

        return sortOut.toString();
    }

    /**
     * <b></b>
     * <p>
     */
    public static List<String> genList(Map<String, String> map) {
        List<String> reList = new ArrayList<String>();

        for (String key : map.keySet()) {
            reList.add(upperFirstCharByStr(key.toLowerCase(), "_"));
            System.out.println(upperFirstCharByStr(key.toLowerCase(), "_"));
        }

        return reList;
    }

}
