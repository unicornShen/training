package generateCode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TurnTool {
    
    private static List<String> baseData() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("person_id");
        stringList.add("person_name");
        stringList.add("birth_yyymmdd");
        stringList.add("remove_yyymmdd");
        stringList.add("remove_time");
        stringList.add("physical_exam_yyymmdd");
        stringList.add("physical_exam_code");
        stringList.add("physical_grade");
        stringList.add("exam_level");
        stringList.add("exam_hospital");
        stringList.add("apply_type");
        stringList.add("apply_reexam_yyymmdd");
        stringList.add("reject_yyymmdd");
        stringList.add("registe_hospital");
        stringList.add("height");
        stringList.add("weight");
        stringList.add("right_eye");
        stringList.add("left_eye");
        stringList.add("rectify_r_eye");
        stringList.add("rectify_l_eye");
        stringList.add("scale_r_eye");
        stringList.add("scale_l_eye");
        stringList.add("blood");
        stringList.add("physical_grade_docu");
        stringList.add("physical_grade_no");
        stringList.add("approval_yyymmdd");
        stringList.add("content");
        stringList.add("user_name");

        
        return stringList;
    }
    
    public static void main(String[] args) {
        for(String str : upFirstCharByStr("_", baseData())){
            System.out.println(str);
        }

    }
    
    public static List<String> upFirstCharByStr(String str, List<String> baseList){
        List<String> strList = new ArrayList<String>();
        
        for (String inputStr : toLower(baseList)) {
            strList.add(upperFirstCharByStr(inputStr, str));
        }
        
        return strList;
    }

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
    

    public static String upFirstStr(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    private static List<String> toLower(List<String> list){
        List<String> reList = new ArrayList<String>();
        for(String str : list){
            reList.add(str.toLowerCase());
        }
        
        return reList;
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
