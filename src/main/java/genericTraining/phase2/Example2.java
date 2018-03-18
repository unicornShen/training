package genericTraining.phase2;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Example1 的擴充版.<br>
 * 
 * 
 * @author Unicorn
 */
public class Example2 {

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
     * 稍微難一點的用法...<br>
     * 傳入的第一個參數必須是 implements IExample 的傢伙。<br>
     * 如果物件關係不熟的話，在此就會稍微看不懂了。
     * <p>
     * 此method的用意在於可以自訂轉換，而不是死板的物件Copy.<br>
     * 用法請看Example2Test.
     */
    public final static <T, S> T turnByConvert(IExample<T, S> exa, S source) {

        //
        // 01. 執行自訂邏輯.
        //
        T target = exa.convert(source);

        //
        // 02. 執行額外的處理(可有可無，是需要而定)。
        //
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            // throw new BusinessException();
        }

        return target;
    }

}
