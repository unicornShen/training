package genericTraining.phase2;

import genericTraining.vo.RayVo;
import genericTraining.vo.UnicornVo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Example3Test {

    /** Test main. */
    public static void main(String[] args) {
        // Test data.
        RayVo vo = generateVo();

        // 改用Example3 才看得出效果.
        UnicornVo hs = Example3.turnByConvert(Example3Test.CONVERT, vo);
        System.out.println("[U]Trace = " + ToStringBuilder.reflectionToString(hs, ToStringStyle.MULTI_LINE_STYLE));

    }

    /**
     * 重點在這!!!!
     * 這種寫法不常見，不過有朝一日會用到的。
     */
    public static final IExample<UnicornVo, RayVo> CONVERT = new IExample<UnicornVo, RayVo>() {

        public UnicornVo convert(RayVo source) {
            // 以下可以使用任意邏輯做處理。
            UnicornVo vo = new UnicornVo();

            try {
                BeanUtils.copyProperties(vo, source);
            } catch (Exception e) {
                e.printStackTrace();
            }

            vo.setAge(source.getAge() + 10);
            vo.getWork().add("play game...");

            return vo;
        }

    };

    public static RayVo generateVo() {
        RayVo vo = new RayVo();
        vo.setAge(18);
        vo.setName("Ray");
        vo.getWork().add("work2");
        vo.getWork().add("work1");
        vo.getWork().add("work3");

        return vo;
    }

}
