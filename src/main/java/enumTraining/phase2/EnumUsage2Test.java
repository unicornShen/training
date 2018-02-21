package enumTraining.phase2;

import java.util.Map;
import java.util.Map.Entry;

import enumTraining.phase2.EnumUsage2.WebsiteItem;
import enumTraining.phase2.EnumUsage2.WebsiteType;

public class EnumUsage2Test {
    
    public static void main(String[] s) {
        onChange(WebsiteType.TYPE_BANK.name());
        //onChange(WebsiteType.TYPE_SEARCH.name());
    }
    
    public static void onChange(final String key){
        final Map<String, String> map = WebsiteItem.MENU.get(key);
        System.out.println("map.size()="+map.size());
        
        for(Entry<String, String> item : map.entrySet()){
            System.out.println(String.format("[%s]:[%s]", item.getKey(), item.getValue()));
        }
    }
    
    //-------------------------------------
    //---- 在實戰中，可以依照實際情況做調整。是不是很簡單?
    //---- 下拉這種東西，個人認為:在enum中是最簡單的用法，也蠻常用到的，
    //---- 但是它真正的威力，在這個範例中，可能沒辦法說服你，一定要用它不可。
    //---- 在下一個phase中會提到enum真正強大之處。
    //-------------------------------------

}
