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
        // map.put("DEPT_CODE", "所屬單位代碼");
        // map.put("MAX_LIMIT", "上限");
        // map.put("", "");
        // map.put("HANDLING_MASTER_SEQ", "交易處理紀錄主檔SEQ");
        // map.put("VVIP_MAX_LIMIT_SETTING_SEQ", "VVIP上限設定主檔序號");
        // map.put("DEPT_CODE", "所屬單位代碼");
        // map.put("MAX_LIMIT", "上限");
        // map.put("", "");
        // map.put("CUSTOMER_SETTING_SEQ", "顧客設定檔序號");
        // map.put("CUSTOMER_ID", "顧客ID");
        // map.put("ON_SERVICE", "是否專人服務");
        // map.put("", "");
        // map.put("VVIP_SERVICE_SETTING_SEQ", "VVIP服務設定主檔序號");
        // map.put("EMP_ID", "員編");
        // map.put("EMP_NAME", "姓名");
        // map.put("DEPT_CODE", "單位");
        // map.put("SORT", "排序");
        // map.put("", "");
        // map.put("HANDLING_MASTER_SEQ", "交易處理紀錄主檔SEQ");
        // map.put("CUSTOMER_SETTING_SEQ", "顧客設定檔序號");
        // map.put("CUSTOMER_ID", "顧客ID");
        // map.put("ON_SERVICE", "是否專人服務");
        // map.put("", "");
        // map.put("HI_VVIP_SERVICE_SETTING_SEQ", "VVIP服務設定主檔序號");
        // map.put("EMP_ID", "員編");
        // map.put("EMP_NAME", "姓名");
        // map.put("DEPT_CODE", "單位");
        // map.put("SORT", "排序");

        return map;
    }

    public static void main(String[] args) {
        // GenerateDto.genDto(turnKey(baseData()));
        // GenerateDto.genDtoForCTBC(baseData());
        GenerateMappingCode.genSetMappingCode(turnKey(baseData()));
        System.out.println("");
        GenerateMappingCode.genSetThisCode(turnKey(baseData()));
        System.out.println("");
        GenerateMappingCode.genGetMappingCode(turnKey(baseData()));
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
