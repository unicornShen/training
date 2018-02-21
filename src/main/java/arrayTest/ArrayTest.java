package arrayTest;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {
        List<String> errMsgList = new ArrayList<String>();
        errMsgList.add("異動記事查無資料");
        errMsgList.add("個人基本查無資料");

        System.out.println(generateErrMsg(errMsgList));
        
        //------------------------
        arrayTest();
        
    }

    private static String generateErrMsg(List<String> errMsgList) {
        StringBuilder errMsg = new StringBuilder();

        for (int size = 0; size <= errMsgList.size() - 1; size++) {
            System.out.println("[U] size = " + size);
            errMsg.append(errMsgList.get(size));

            if (size != errMsgList.size() - 1) {
                errMsg.append("，");
            } else {
                errMsg.append("。");
            }
        }

        return errMsg.toString();
    }
    
    private static void arrayTest(){
        for(int i = 0; i < 1; i++){
            System.out.println("i = " + i);
        }
    }
}
