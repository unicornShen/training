package generateCodeTest;

public class RefineTSU {
    private static boolean debugFlag = false;
    
    public static void main(String[] args) {
        System.out.println(generateInsertString(getData()));
    }

    /** 資料來源:ToStringBuilder. */
    private static String[] getData() {
        String[] array =
            {
              "noticeType=T901"
              ,"personId=K199000227"
              ,"areaCode=65000030"
              ,"noticeDate=1020513"
              ,"noticeTime=103355"
              ,"ckDateTime=102031714034004"
              ,"errorMessage=失敗"
              ,"noticeContent=K199000227,1020317,5,0830110,CX317,K199000227,李廣二,1,020,新北市,083,106,1,235,51,新北市中和區建和里００７鄰連城路２６３巷２７弄６號３樓,1,1020731,AA,0000004,ＸＸ字, ,102031714034004,1020513, ,65000030, , ," 
              ,"errorCode=RS6031"
              ,"printData= "
              ,"processDateTime=20130513103355000"
              ,"processMode=C"

             , "transaction_id=TX0CRD1305131033539561" };

        return array;
    }

    /** 產生DB的Insert statement. */
    private static String generateInsertString(String[] array) {
        final StringBuilder sql = new StringBuilder();

        sql.append(" insert into mlde901w ( ");
        for (int i = 0; i < array.length; i++) {
            String col[] = array[i].split("=");
            sql.append(genColumnName(col[0]));

            if (i != array.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(") \n");

        sql.append(" values( ");
        for (int i = 0; i < array.length; i++) {
            String ars[] = array[i].split("=");
            sql.append("'" + ars[1] + "'");

            if (i != array.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(" ); ");

        return sql.toString();
    }

    /** 找大寫字. */
    private static int findUpperWord(final String str) {
        int site = -1;
        for (int i = 0; i < str.length(); i++) {
            log("str.charAt(i) = " + str.charAt(i));
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                log("i = " + i);
                return i;
            }
        }

        return site;
    }

    /** 插入底線. */
    private static String insertedLine(final String str, final int index) {
        final StringBuilder reStr = new StringBuilder();
        reStr.append(str.substring(0, index));
        reStr.append("_");
        reStr.append(str.substring(index, index + 1).toLowerCase());
        reStr.append(str.substring(index + 1));

        log("reStr = " + reStr);
        return reStr.toString();
    }

    /** 遞回. */
    private static String genColumnName(final String field) {
        log("field = " + field);
        String columnName = "";

        int site = findUpperWord(field);
        if (site == -1) {
            return field;

        } else {
            columnName = genColumnName(insertedLine(field, site));
        }

        return columnName;
    }

    /** Log. */
    private static void log(String string) {
        if (debugFlag) {
            System.out.println(string);
        }
    }
}
