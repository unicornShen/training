package genericTraining.phase2;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 用到T這種定義式的泛型，最常出現在:<br>
 * 希望method回傳一個明確的類型，但傳入參數是不明確的。
 * <p>
 * 以下，用複製物件的案例來做範例。
 * 
 * @author Unicorn
 */
public class Example1 {

    /**
     * 最直覺最簡單的寫法，<br>
     * 但是使用時必須先new一個target的物件.
     */
    public <T, S> void turn1(T target, S source) {
        try {
            // 這邊使用'阿怕棄'的 BeanUtils.
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * References turn1.<br>
     * 也可以回傳.
     */
    public <T, S> T turn2(T target, S source) {
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 改善turn1 必須先new一個物件的寫法:<br>
     * 改傳入參數，傳入你想轉成哪種類別。
     */
    public <T, S> T turn3(Class<T> cls, S source) {
        T target = null;

        try {
            target = cls.newInstance();
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            // Throw turn failed error message.
            e.printStackTrace();
        }

        // 基本上跑到這，就不會回傳空的。
        return target;
    }

    /**
     * 也許某些狀況會寫成這樣。<br>
     * Throw runtime exception. 
     */
    public <T, S> T turn4(Class<T> cls, S source) {
        try {
            T target = cls.newInstance();
            BeanUtils.copyProperties(target, source);

            return target;
        } catch (Exception e) {
            throw new RuntimeException("");
        }

    }

    /**
     * 若要把此類別變成工具列別，會寫成這樣。<br>
     * 前面加上 final static 修飾子。
     */
    public final static <T, S> T turn5(Class<T> cls, S source) {
        T target = null;

        try {
            target = cls.newInstance();
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            // 在此應該會回傳一個系統自訂的Exception，如下:
            // throw new BusinessException();
        }

        return target;
    }

}
