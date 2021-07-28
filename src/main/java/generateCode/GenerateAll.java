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
        map.put("CLASS_CODE", "類別代碼");
        map.put("CASE_TYPE", "案件分類");
        map.put("REMIT_TYPE", "匯款方式");
        map.put("TRANSFER_WAY", "發電通路");
        map.put("ITEM_CODE", "項目代碼");
        map.put("DETAIL_CODE", "細項代碼");
        map.put("CM_CODE", "複選代碼");
        map.put("SG_CODE", "單選代碼");
        map.put("NUMBER_1", "數值1");
        map.put("NUMBER_2", "數值2");
        map.put("NUMBER_3", "數值3");
        map.put("NUMBER_4", "數值4");
        map.put("NUMBER_5", "數值5");
        map.put("NUMBER_6", "數值6");
        map.put("TEXT_1", "文字1");
        map.put("TEXT_2", "文字2");
        map.put("TEXT_3", "文字3");
        map.put("CURR_CODE", "幣別條件");
        map.put("FEE_CCY", "計價幣別");
        map.put("CUST_ID", "顧客ID");
        map.put("VALID_DATE", "到期日");
        map.put("APPLY_UNIT", "申請單位");
        map.put("CONTRACT_NO", "議約編號");
        map.put("TYPE", "優惠類型");
        map.put("APPLY_CHANNEL", "申請通路");
        map.put("FEE_NORM_TYPE", "收費標準類型");

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
