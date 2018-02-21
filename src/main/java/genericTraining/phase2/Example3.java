package genericTraining.phase2;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Reference Example2.
 * Method trunByConvert different.
 * 
 * @author Unicorn
 */
public class Example3 {

    /**
     * Example1's method.
     */
    public final static <T, S> T turn(Class<T> cls, S source) {
        T target = null;

        try {
            target = cls.newInstance();
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            // throw new BusinessException();
        }

        return target;
    }

    /**
     * 自定轉換.
     * 這邊額外處理的部分拿掉了.
     */
    public final static <T, S> T turnByConvert(IExample<T, S> exa, S source) {

        // 01. 執行自訂邏輯.
        T target = exa.convert(source);

        // 02. 執行額外的處理(可有可無，是需要而定)。
        // 不做任何事.

        return target;
    }

}
