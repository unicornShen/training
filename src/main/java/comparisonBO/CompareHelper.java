package comparisonBO;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author Unicorn
 */
public class CompareHelper {
    /** 元素名稱 */
    private String Name;

    /** 元素型態 */
    private Type Type;

    /** DB原始值 */
    private Object oldData;

    /** 歷程的值 */
    private Object newData;

    /** 比對結果, 是否不同 */
    private boolean isDifferent;

    private Object oldDataBo;

    private Object newDataBo;

    private Map<String, CompareHelper> innerFieldMap = new HashMap<String, CompareHelper>();

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Type getType() {
        return this.Type;
    }

    public void setType(Type type) {
        this.Type = type;
    }

    public Object getNewData() {
        return this.newData;
    }

    public void setNewData(Object newData) {
        this.newData = newData;
    }

    public Object getOldData() {
        return this.oldData;
    }

    public void setOldData(Object oldData) {
        this.oldData = oldData;
    }

    public boolean isDifferent() {
        return this.isDifferent;
    }

    public void setDifferent(boolean isDifferent) {
        this.isDifferent = isDifferent;
    }

    public Object getOldDataBo() {
        return this.oldDataBo;
    }

    public void setOldDataBo(Object oldDataBo) {
        this.oldDataBo = oldDataBo;
    }

    public Object getNewDataBo() {
        return this.newDataBo;
    }

    public void setNewDataBo(Object newDataBo) {
        this.newDataBo = newDataBo;
    }

    public Map<String, CompareHelper> getInnerFieldMap() {
        return this.innerFieldMap;
    }

    public void setInnerFieldMap(final Map<String, CompareHelper> innerFieldMap) {
        this.innerFieldMap = innerFieldMap;
    }

    /**
     * <b>比對兩個物件</b>
     * <p>
     */
    public static final Map<String, CompareHelper> comparisonBO(final Object beforeVo, final Object afterVo) {
        final Map<String, CompareHelper> returnMap = new HashMap<String, CompareHelper>();

        for (Field f : beforeVo.getClass().getDeclaredFields()) {
            final CompareHelper result = new CompareHelper();
            try {
                Object beforeValue = PropertyUtils.getProperty(beforeVo, f.getName());
                Object afterValue = PropertyUtils.getProperty(afterVo, f.getName());

                result.setName(f.getName());
                result.setOldData(beforeValue);
                result.setNewData(afterValue);
                result.setType(f.getGenericType());
                result.setOldDataBo(beforeVo);
                result.setNewDataBo(afterVo);

                if (beforeValue != null && afterValue != null) {
                    result.setDifferent(!beforeValue.equals(afterValue));

                } else if (beforeValue == null && afterValue == null) {
                    // 兩個值都是空的話就當作一樣。
                    result.setDifferent(false);

                } else {
                    result.setDifferent(true);
                }

                returnMap.put(f.getName(), result);

                System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));

            } catch (Exception e) {
                // 有些元素有可能沒對應的get會拋出Exception，此為正常
                // e.printStackTrace();
            }
        }

        return returnMap;
    }

    /**
     * <b>比對兩個物件</b>
     * <p>
     */
    public static final Map<String, CompareHelper> comparisonBO(//
            final Object beforeVo, final Object afterVo, final List<String> nameList) {
        final Map<String, CompareHelper> returnMap = new HashMap<String, CompareHelper>();

        for (Field f : beforeVo.getClass().getDeclaredFields()) {
            final CompareHelper result = new CompareHelper();
            try {
                Object beforeValue = PropertyUtils.getProperty(beforeVo, f.getName());
                Object afterValue = PropertyUtils.getProperty(afterVo, f.getName());

                // 遞回
                if (nameList.contains(f.getName())) {
                    result.setInnerFieldMap(comparisonBO(beforeValue, afterValue));
                }

                result.setName(f.getName());
                result.setOldData(beforeValue);
                result.setNewData(afterValue);
                result.setType(f.getGenericType());
                result.setOldDataBo(beforeVo);
                result.setNewDataBo(afterVo);

                if (beforeValue != null && afterValue != null) {
                    result.setDifferent(!beforeValue.equals(afterValue));

                } else if (beforeValue == null && afterValue == null) {
                    // 兩個值都是空的話就當作一樣。
                    result.setDifferent(false);

                } else {
                    result.setDifferent(true);
                }

                returnMap.put(f.getName(), result);

                System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));

            } catch (Exception e) {
                // 有些元素有可能沒對應的get會拋出Exception，此為正常
                // e.printStackTrace();
            }
        }

        return returnMap;
    }
}
