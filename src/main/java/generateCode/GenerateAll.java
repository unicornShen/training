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
        map.put("Bulletin_Board_ToDo", "公佈欄-待辦事項");
        map.put("SEQ", "序號");
        map.put("YEAR", "查核年");
        map.put("MONTH", "查核月");
        map.put("AREA_ID", "區域代碼");
        map.put("BRANCH_NBR", "分行代碼");
        map.put("CHECK_FUNCTION", "各項查核");
        map.put("CHECK_ITEM", "提醒事項");
        map.put("CHECK_TOTAL", "應查核數");
        map.put("UNCHECK_COUNT", "未查核人數");
        map.put("TEMP_COUNT", "暫存數");
        map.put("TODO_COUNT", "待處理數");
        map.put("ADD_PROBLEM_COUNT", "新增問題數");
        map.put("CHECK_SEQ", "問題種類 SEQ");
        map.put("TXN_ID", "頁面ID");
        map.put("SPECIAL_HOLD_MAIL", "保密戶(區域)(y/n)");
        map.put("IS_DELAY", "是否超過查核時間(y/n)");
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

