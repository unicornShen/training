package queryMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class TestSelect {

    //    StringBuilder sql = new StringBuilder();
    //    sql.append(" select ma.store_Name, st.store_No, st.act_Date, st.slip_Type ");
    //    sql.append(" , st.inventory_Date, st.item_No, st.seq, st.price ");
    //    sql.append(" , st.store_Cost, st.sale_Cnt, st.stock_Cnt, st.upd_Flag, st.upd_Id, st.upd_Date ");
    //    sql.append(" from St_65_F st ");
    //    sql.append(" join Ma_Store_Eff ma on st.store_no = ma.store_no ");
    //    sql.append(" where st.store_no = :storeNo ");
    //
    //    Map<String, Object> params = new HashMap<>();
    //    params.put("storeNo", "110068");
    //
    //    List<Object[]> dataList = this.findBySQLWithQueryObject(sql.toString(), params, null, null);
    //    List<St65FModel> list = generateModelList(dataList, sql.toString(), St65FModel.class);

    public static void main(String[] args) {
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT A.LINE_NO,                                 ");
        sql.append("   A.ITEM_NO,                                      ");
        sql.append("   B.ITEM_NAME,                                    ");
        sql.append("   A.STORE_COST,                                   ");
        sql.append("   A.PRICE,                                        ");
        sql.append("   A.VENDOR_COST,                                  ");
        sql.append("   A.FREIGHT,                                      ");
        sql.append("   A.ACT_QTY,                                      ");
        sql.append("   A.ACCOUNT_TYPE,                                 ");
        sql.append("   A.TAX_TYPE,                                     ");
        sql.append("   A.PMA,                                          ");
        sql.append("   (A.STORE_COST  * A.ACT_QTY) AS STORE_COST_TOTA, ");
        sql.append("   (A.PRICE       * A.ACT_QTY) AS PRICE_TOTAL,     ");
        sql.append("   (A.VENDOR_COST * A.ACT_QTY) AS VENDOR_COST_TOT, ");
        sql.append("   (A.FREIGHT     * A.ACT_QTY) AS FREIGHT_TOTAL    ");
        sql.append(" FROM DL_ADJ_D_F A                                 ");
        sql.append(" JOIN MA_ITEM_EFF B                                ");
        sql.append("   ON B.ITEM_NO = A.ITEM_NO                        ");
        sql.append(" WHERE A.PRE_SLIP_TYPE = :preSlipType              "); // 憑證區分
        sql.append(" AND   A.ADJ_TYPE      = :adjType                  "); // 調帳類型
        sql.append(" AND   A.STORE_NO      = :storeNo                  "); // 門市代號
        sql.append(" AND   A.DELIVERY_DATE = :deliveryDate             "); // 進退貨日
        sql.append(" AND   A.SLIP_NO       = :slipNo                   "); // 訂單編號
        sql.append(" AND   A.VENDOR_NO     = :vendorNo                 "); // 廠商編號
        sql.append(" AND   A.DELIVERY      = :delivery                 "); // 趟次
        sql.append(" AND   A.ACT_DATE      = :actDate                  "); // 會計日
        sql.append(" AND   A.REC_DATE      = :recDate                  "); // 入帳日
        sql.append(" AND   A.ADJ_DATE      = :adjDate                  "); // 調帳日


                generateFieldList(sql.toString());

        St65FModel vo = new St65FModel();
        Field[] fs = vo.getClass().getSuperclass().getDeclaredFields();
        for (Field f : fs) {
            System.out.println(f.getName());
        }

    }

    /**
     * 將使用 SQL 查詢回來的 List< Object[] > 轉換成 List< VO >.
     * 
     * @param list SQL 查詢結果
     * @param sql SQL 查詢條件； 查詢中若有使用別名，請使用 'AS' 的關鍵字.
     * @param cls 轉換類別.
     */
    public static final <T> List<T> generateModelList(final List<Object[]> list, final String sql, final Class<T> cls) {
        final List<String> fieldList = generateFieldList(sql);
        final List<T> resultList = new ArrayList<T>();

        try {
            T targetVo = cls.newInstance();

            final List<String> fields = new ArrayList<String>();
            for (Field f : targetVo.getClass().getDeclaredFields()) {
                fields.add(f.getName());
            }

            for (Object[] obj : list) {

                for (int i = 0; i < obj.length; i++) {
                    if (fields.contains(fieldList.get(i))) {
                        PropertyUtils.setProperty(targetVo, fieldList.get(i), obj[i]);

                    } else {
                        // System.out.println(fieldList.get(i) + ":" + obj[i]);
                    }
                }

                resultList.add(targetVo);

            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return resultList;
    }

    /** 產生所有 column 對應的 field name 清單. */
    private static List<String> generateFieldList(final String sql) {
        final int startNum = StringUtils.indexOf(StringUtils.upperCase(sql.toString()), "SELECT ") + 6;
        final int endNum = StringUtils.indexOf(StringUtils.upperCase(sql.toString()), " FROM ");

        final String fieldStr = StringUtils.substring(sql, startNum, endNum);
        final String[] fieldList = StringUtils.split(fieldStr, ",");

        final List<String> relFieldList = new ArrayList<String>();
        for (String field : fieldList) {
            relFieldList.add(getRelFieldName(field));
        }

        return relFieldList;
    }

    /** 取出 column name. */
    private static String getRelFieldName(final String field) {
        String reStr = StringUtils.trimToEmpty(field);

        final int asIndex = StringUtils.indexOf(StringUtils.upperCase(reStr), " AS ");
        if (asIndex != -1) {
            reStr = StringUtils.substring(reStr, asIndex + 4);

        } else {
            final int startIndex = StringUtils.indexOf(reStr, ".") + 1;
            reStr = StringUtils.substring(reStr, startIndex);
        }

        return turnToVoName(reStr);
    }

    /** 轉為駝峰式命名. */
    private static final String turnToVoName(final String turnStr) {
        final StringBuilder reStr = new StringBuilder();
        final String[] arrayStr = StringUtils.split(turnStr, "_");

        for (int i = 0; i < arrayStr.length; i++) {
            final String[] splitStr = arrayStr[i].split("");
            for (int j = 0; j < splitStr.length; j++) {
                if (j == 1 && i != 0) {
                    reStr.append(StringUtils.upperCase(splitStr[j]));
                } else {
                    reStr.append(StringUtils.lowerCase(splitStr[j]));
                }
            }
        }

        System.out.println(reStr);
        return reStr.toString();
    }
}
