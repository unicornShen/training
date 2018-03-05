package convert;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * <pre>
 * 資料轉換相關工具類別.
 * </pre>
 *
 * @since 2017年6月22日
 * @author Unicorn
 * @version <ul>
 *            <li>2017年6月22日,Unicorn,new
 *          </ul>
 */
public class ConvertUtil {

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtil.class);

    /**
     * 參數 List 轉 物件.
     * 
     * @param <T> 目標泛型
     * @param dataList 參數 List< map > 
     * @param cls 指定型別
     * @return 回傳指定型別.
     */
    public static <T> List<T> paramListToObject(final List<Map<String, ?>> dataList, final Class<T> cls) {
        final List<T> resultList = new ArrayList<>();

        if (CollectionUtils.isEmpty(dataList)) {
            LOGGER.info("ConvertUtil.paramListToObject() param dataList is null!");
            return resultList;
        }

        for (Map<String, ?> map : dataList) {
            resultList.add(paramMapToObject(map, cls));
        }

        return resultList;
    }

    /**
     * 參數 map 轉 物件.
     * 
     * @param <T> 目標泛型
     * @param map 參數 map 
     * @param cls 指定型別
     * @return 回傳指定型別.
     */
    public static <T> T paramMapToObject(final Map<String, ?> map, final Class<T> cls) {
        if (map == null) {
            LOGGER.info("ConvertUtil.paramMapToObject() param map is null!");
            return null;
        }

        T targetObj = null;
        try {
            targetObj = cls.newInstance();
            for (Field f : targetObj.getClass().getDeclaredFields()) {
                if (StringUtils.equalsIgnoreCase(f.getName(), "serialVersionUID")) {
                    continue;
                }

                try {
                    PropertyUtils.setProperty(targetObj, f.getName(), mappingValueType(f, map.get(f.getName())));
                } catch (Exception e) {
                    LOGGER.warn("ConvertUtil.paramMapToObject set property error! {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("ConvertUtil.paramMapToObject newInstance() error! {}", e.getMessage());
        }

        return targetObj;
    }

    /**
     * 物件轉參數 map. 
     * 
     * @param obj VO 物件.
     * @return 參數 map
     */
    public static Map<String, Object> objectToParamMap(final Object obj) {
        final Map<String, Object> rtnMap = new HashMap<>();

        for (Field f : obj.getClass().getDeclaredFields()) {
            if (StringUtils.equalsIgnoreCase(f.getName(), "serialVersionUID")) {
                continue;
            }

            try {
                rtnMap.put(f.getName(), PropertyUtils.getProperty(obj, f.getName()));

            } catch (Exception e) {
                LOGGER.warn("ConvertUtil.convertToParamMap error! {}", e.getMessage());
            }
        }

        return rtnMap;
    }

    /**
     * Object list to VO list.
     * 
     * @param objList 來源物件清單
     * @param cls 目標物件型態
     * @param <T> 目標泛型
     * @return List<T>
     */
    public static <T> List<T> objToVo(final List<Object> objList, final Class<T> cls) {
        final List<T> rtnList = new ArrayList<>();

        for (Object obj : objList) {
            rtnList.add(objToVo(obj, cls));
        }

        return rtnList;
    }

    /**
     * Object to list.
     * 
     * @param <T> 目標泛型
     * @param obj 來源物件
     * @param cls 目標物件型態
     * @return T
     */
    public static <T> T objToVo(final Object obj, final Class<T> cls) {
        T targetObj = null;

        try {
            targetObj = cls.newInstance();
            for (Field f : targetObj.getClass().getDeclaredFields()) {
                if (StringUtils.equalsIgnoreCase(f.getName(), "serialVersionUID")) {
                    continue;
                }

                try {
                    final Object value = PropertyUtils.getProperty(obj, f.getName());
                    PropertyUtils.setProperty(targetObj, f.getName(), value);

                } catch (Exception e) {
                    LOGGER.warn("ConvertUtil.objToVo set property error! {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("ConvertUtil.objToVo newInstance() error! {}", e.getMessage());
        }

        return targetObj;
    }

    /**
     * JSON object to map.
     * <p>
     * JSON object 內含 JSON array 時使用. 
     * 
     * @param json JSON object
     * @return map
     */
    public static Map<String, Object> jsonToMap(final JsonObject json) {
        final Map<String, Object> map = new LinkedHashMap<>();
        for (Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getValue().isJsonNull()) {
                map.put(entry.getKey(), null);

            } else if (entry.getValue().isJsonObject()) {
                final JsonObject jsonObj = entry.getValue().getAsJsonObject();
                map.put(entry.getKey(), jsonToMap(jsonObj));

            } else if (entry.getValue().isJsonArray()) {
                final JsonArray jsonArray = entry.getValue().getAsJsonArray();
                final Gson googleJson = new Gson();

                @SuppressWarnings("unchecked")
                final List<Map<String, Object>> jsonObjList = googleJson.fromJson(jsonArray, ArrayList.class);
                map.put(entry.getKey(), jsonObjList);

            } else {
                map.put(entry.getKey(), entry.getValue().getAsString());
            }
        }

        return map;
    }

    /**
     * Map to VO.
     * 
     * @param <T> 目標泛型
     * @param map 來源 Map
     * @param clz 目標物件型態
     * @return T
     */
    public static <T> T mapToVo(final Map<String, ?> map, final Class<T> clz) {
        T target = null;
        try {
            target = clz.newInstance();

            for (Field f : clz.getDeclaredFields()) {
                if (StringUtils.equalsIgnoreCase(f.getName(), "serialVersionUID")) {
                    continue;
                }

                try {
                    final Object value = map.get(f.getName());
                    PropertyUtils.setProperty(target, f.getName(), mappingValueType(f, value));

                } catch (Exception e) {
                    LOGGER.warn("ConvertUtil.mapToVo set property error! {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("ConvertUtil.mapToVo error! {}", e.getMessage());
        }

        return target;
    }

    /**
     * Mapping value type.
     * 
     * @param f Field
     * @param value 
     * @return Object 回傳符合類型的值
     */
    private static Object mappingValueType(final Field f, final Object value) {
        Object result = value;

        if (StringUtils.equals(f.getGenericType().getTypeName(), BigDecimal.class.getName())) {
            result = new BigDecimal(ObjectUtils.toString(value));

        } else if (StringUtils.equals(f.getGenericType().getTypeName(), String.class.getName())) {
            result = ObjectUtils.toString(value);

        } else if (StringUtils.equals(f.getGenericType().getTypeName(), Integer.class.getName())) {
            result = NumberUtils.toInt(ObjectUtils.toString(value));

        } else if (StringUtils.equals(f.getGenericType().getTypeName(), int.class.getName())) {
            result = NumberUtils.toInt(ObjectUtils.toString(value));

        } else if (StringUtils.equals(f.getGenericType().getTypeName(), boolean.class.getName())) {
            result = BooleanUtils.toBoolean(ObjectUtils.toString(value));

        } else {
            LOGGER.info("ConvertUtil.mappingValueType(): This type not hanlde:{}", f.getGenericType().getTypeName());
        }

        return result;
    }

    //-------------------------------------
    //----
    //-------------------------------------

    /**
     * 將 SQL 參數 MAP 轉為查詢條件.</br>
     * 產出的 condition 為 WHERE 開頭.
     * 
     * @param condition SQL 參數 MAP.
     * @return String 
     */
    public static String genDynamicConditionWithWhere(final Map<String, Object> condition) {
        return StringUtils.replaceOnce(genDynamicCondition(condition), " AND ", " WHERE ");
    }

    /**
     * 將 SQL 參數 MAP 轉為查詢條件.</br>
     * 產出的 condition 為 WHERE 開頭.
     * 
     * @param condition SQL 參數 MAP.
     * @param alias Table 別名.
     * @return String 
     */
    public static String genDynamicConditionWithWhere(final Map<String, Object> condition, final String alias) {
        return StringUtils.replaceOnce(genDynamicCondition(condition, alias), " AND ", " WHERE ");
    }

    /**
     * 將 SQL 參數 MAP 轉為查詢條件.</br>
     * 產出的 condition 為 ADN 開頭.
     * 
     * @param condition SQL 參數 MAP.
     * @return String 
     */
    public static String genDynamicCondition(final Map<String, Object> condition) {
        return genDynamicCondition(condition, null);
    }

    /**
     * 將 SQL 參數 MAP 轉為查詢條件.</br>
     * 產出的 condition 為 ADN 開頭.
     * 
     * @param condition SQL 參數 MAP.
     * @param alias Table 別名.
     * @return String 
     */
    public static String genDynamicCondition(final Map<String, Object> condition, final String alias) {
        final StringBuilder dynamicSql = new StringBuilder();
        dynamicSql.append(System.lineSeparator());
        if (condition != null) {
            for (Entry<String, Object> entry : condition.entrySet()) {
                if (StringUtils.isBlank(ObjectUtils.toString(entry.getValue()))) {
                    continue;
                }

                dynamicSql.append(" AND ");
                if (StringUtils.isNotBlank(alias)) {
                    dynamicSql.append(alias);
                    dynamicSql.append(".");
                }
                dynamicSql.append(entry.getKey());
                dynamicSql.append(" = '");
                dynamicSql.append(entry.getValue());
                dynamicSql.append("' ");
                dynamicSql.append(System.lineSeparator());
            }
        }

        return dynamicSql.toString();
    }

    //-------------------------------------
    //----
    //-------------------------------------

    /**
     * Set empty value.
     * 
     * @param list Data list.
     */
    public static void setDefaultEmpty(final List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        for (Object obj : list) {
            setDefaultEmpty(obj);
        }
    }

    /**
     * Set empty value.
     * 
     * @param obj Data object
     */
    public static void setDefaultEmpty(final Object obj) {
        for (Field f : obj.getClass().getDeclaredFields()) {
            if (StringUtils.equals(f.getGenericType().getTypeName(), BigDecimal.class.getName())) {
                setDefaultValue(obj, f, BigDecimal.ZERO);

            } else if (StringUtils.equals(f.getGenericType().getTypeName(), String.class.getName())) {
                setDefaultValue(obj, f, StringUtils.EMPTY);

            } else if (StringUtils.equals(f.getGenericType().getTypeName(), Integer.class.getName())) {
                setDefaultValue(obj, f, NumberUtils.INTEGER_ZERO);
            }
        }
    }

    /** Set default value. */
    private static void setDefaultValue(Object obj, Field f, Object defaultValue) {
        try {
            Object value = PropertyUtils.getProperty(obj, f.getName());
            if (value == null) {
                PropertyUtils.setProperty(obj, f.getName(), defaultValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 將 Map 中所有資料轉為 String 型態.
     * 
     * @param map 資料 map.
     * @return 轉換後的 map.
     */
    public static Map<String, Object> setAllToString(final Map<String, ?> map) {
        final Map<String, Object> resultMap = new HashMap<>();
        if (map == null) {
            return resultMap;
        }

        for (Entry<String, ?> entry : map.entrySet()) {
            resultMap.put(entry.getKey(), ObjectUtils.toString(entry.getValue()));
        }

        return resultMap;
    }

}
