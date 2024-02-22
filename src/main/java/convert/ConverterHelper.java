package convert;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

/**
 *
 */
public class ConverterHelper {

    private static final Logger LOGGER = Logger.getLogger(ConverterHelper.class);

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
            final List<String> fields = new ArrayList<String>();
            for (Field f : cls.getDeclaredFields()) {
                fields.add(f.getName());
            }

            for (Object[] obj : list) {
                T targetVo = cls.newInstance();

                for (int i = 0; i < obj.length; i++) {
                    if (fields.contains(fieldList.get(i))) {
                        try {
                            PropertyUtils.setProperty(targetVo, fieldList.get(i), mappingValue(targetVo, fieldList.get(i), obj[i]));

                        } catch (Exception e) {
                            LOGGER.warn("[WARN] The field (" + fieldList.get(i) + "): " + e.getMessage());
                            // throw new PropertyException("[Warn] The field (" + fieldList.get(i) + "): " + e.getMessage());
                        }

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
        final int startNum = StringUtils.indexOf(StringUtils.upperCase(sql.toString()), " SELECT ") + 7;
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

        return reStr.toString();
    }

    /** 轉換為真正的資料類別. */
    private static Object mappingValue(final Object srcObj, final String fieldName, final Object value) throws Exception {
        final Field field = srcObj.getClass().getDeclaredField(fieldName);
        return mappingValue(field, value);
    }

    /** 轉換為真正的資料類別. */
    private static Object mappingValue(final Field field, final Object value) {
        final Type type = field.getGenericType();

        if (value == null) {
            return value;
        }

        final String strValue = Objects.toString(value);
        if (type.equals(int.class)) {
            return NumberUtils.toInt(strValue);
        } else if (type.equals(Integer.class)) {
            return NumberUtils.toInt(strValue);
        } else if (type.equals(long.class)) {
            return NumberUtils.toLong(strValue);
        } else if (type.equals(Long.class)) {
            return NumberUtils.toLong(strValue);
        } else if (type.equals(BigDecimal.class)) {
            return new BigDecimal(strValue);
        } else if (type.equals(Date.class)) {
            return value; // 
        } else {
            return strValue;
        }
    }

    public static void main(String[] args) {

        ConvertModel vo = new ConvertModel();
        for (Field f : vo.getClass().getDeclaredFields()) {
            System.out.println(f.getName());
            System.out.println(f.getGenericType());

            Type type = f.getType();
            System.out.println(type);

            Type intType = Integer.class;
            System.out.println(intType);

            System.out.println(type.equals(intType)); // OK
            System.out.println(f.getType().equals(Integer.class)); // OK
            System.out.println(f.getGenericType().equals(Integer.class)); // OK
            System.out.println("-------------------");
        }

        try {
            Field f = vo.getClass().getDeclaredField("id");
            System.out.println(f.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
