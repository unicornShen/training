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
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("PBSS_CODE", "PBSS Code");
        map.put("CURRENCY", "幣別");
        map.put("ROI", "報酬率級距");
        map.put("TOTAL_CNT", "客戶數");
        map.put("TOTAL_AMT", "金額總計");
        map.put("UPDATE_DATE", "最後異動日期");
        map.put("CREATOR", "建立者");
        map.put("CREATETIME", "建立時間");
        map.put("MODIFIER", "更新者");
        map.put("LASTUPDATE", "更新時間");

        return map;
    }
    
    public static void main(String[] args){
//        GenerateDto.genDto(turnKey(baseData()));
        GenerateDto.genDtoForCTBC(baseData());
        GenerateMappingCode.genSetMappingCode(turnKey(baseData()));
        System.out.println("");
        GenerateMappingCode.genSetThisCode(turnKey(baseData()));
        System.out.println("");
        // GenerateMappingCode.genGetMappingCode(turnKey(baseData()));
        genList(baseData());
    }
    
    public static Map<String, String> turnKey(Map<String, String> map){
        Map<String, String> reMap = new LinkedHashMap<String, String>();
        
        for(String key : map.keySet()){
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

        if(StringUtils.isNotEmpty(temp)){
            return temp;
        }
        
        return sortOut.toString();
    }
    
    /**
     * <b></b>
     * <p>
     */
    public static List<String> genList(Map<String, String> map){
        List<String> reList = new ArrayList<String>();
        
        for(String key : map.keySet()){
            reList.add(upperFirstCharByStr(key.toLowerCase(), "_"));
            System.out.println(upperFirstCharByStr(key.toLowerCase(), "_"));
        }
        
        
        return reList;
    }

}

