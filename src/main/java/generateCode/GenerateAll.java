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
        map.put("mao_consumer_cat_points_history", "貓糧使用紀錄");
        map.put("mao_consumer_cat_points_history_seq", "貓糧使用紀錄序號");
        map.put("mao_consumer_transaction_master_seq", "消費者交易主檔序號");
        map.put("used_points", "使用點數");

        return map;
    }

    public static void main(String[] args) {
        //        GenerateDto.genDto(turnKey(baseData()));
//        GenerateDto.genDtoForCTBC(baseData());
        GenerateMappingCode.genSetMappingCode(turnKey(baseData()));
        System.out.println("");
        GenerateMappingCode.genSetThisCode(turnKey(baseData()));
        System.out.println("");
        // GenerateMappingCode.genGetMappingCode(turnKey(baseData()));
        genList(baseData());
        System.out.println("");

        
        System.out.println("[Model]------------------------------------------------");
        GenerateMao.genModel(baseData());
        System.out.println("[RPY]------------------------------------------------");
        GenerateMao.genRpy(baseData());
        System.out.println("[DAO]------------------------------------------------");
        GenerateMao.genDao(baseData());
        System.out.println("[Service]------------------------------------------------");
        GenerateMao.genService(baseData());
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
