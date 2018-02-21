package generateFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class GenerateCodeRefType {
    public enum FileType {
        // ACNC
        ACNCNEWS("ACNCNEWS", 0, 6), // 報紙月結檔 

        // ACAC
        AACSRREG("AACSRREG", 8, 14), // 現金日報
        AA111REG("AA111REG", 8, 14), // 銷項發票明細
        AA202REG("AA202REG", 8, 14), // 代售商品明細
        AA11EREG("AA11EREG", 8, 14), // 受託代銷商品銷售明細
        AA116REG("AA116REG", 8, 14), // 商品兌換券卡彙總
        AA402REG("AA402REG", 8, 14), // 廠商代現金明細
        AA409REG("AA409REG", 8, 14), // 其他卡上傳明細
        AA601REG("AA601REG", 8, 14), // 現金進退貨明細
        AAUNIINV("AAUNIINV", 0, 6), // 門市中獎發票兌換金額檔

        ;

        final private String zipName;
        final private int startNumber;
        final private int endNumber;

        public String getZipName() {
            return this.zipName;
        }

        public int getStartNumber() {
            return this.startNumber;
        }

        public int getEndNumber() {
            return this.endNumber;
        }

        /**
         * Constructor. @param zipName
         * Constructor. @param startNumber
         * Constructor. @param endNumber
         */
        private FileType(String zipName, int startNumber, int endNumber) {
            this.zipName = zipName;
            this.startNumber = startNumber;
            this.endNumber = endNumber;
        }

        private static final Map<String, FileType> TYPE_MAP;
        static {
            final Map<String, FileType> map = new HashMap<String, FileType>();
            for (FileType type : FileType.values()) {
                map.put(type.getZipName(), type);
            }

            TYPE_MAP = Collections.unmodifiableMap(map);
        }

        public static FileType lookup(final String fileName) throws Exception {
            if (TYPE_MAP.containsKey(StringUtils.substring(fileName, 0, 8))) {
                return TYPE_MAP.get(StringUtils.substring(fileName, 0, 8));

            } else {
                // throw new UnsupportedAttributeException("未定義: " + fileName, fileName);
                throw new Exception("UnsupportedAttributeException 未定義:  + fileName, fileName");
            }
        }

    }

    /** Test code. */
    public static void main(String[] args) {
        //        FileType type = FileType.lookup("ACNCNEWS1219172254");
        //        System.out.println(type.getZipName());
        //        
        //        String str = "00976150";
        //        System.out.println("compareTo="+str.compareTo("00976149"));
        //        
        //        String data = "20161220999898202 5035900000002000000000020000000002";
        //        System.out.println(StringUtils.substring(data, 8, 14));

        //        String cStr = "AA111REG";
        //        System.out.println(StringUtils.contains(cStr, cStr));

//        String no = "01234567890";
//        System.out.println(StringUtils.substring(no, 0, 8));
        
        String str = "H010499995720161220   12190253341683490963120161220100148639600198150014848930019790 9999572";
        
        System.out.println(StringUtils.replace(str, "9999572", "999957B", 1));
        
        
        System.out.println(StringUtils.substring("00123456", 2));

    }
}
