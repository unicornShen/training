package genericTraining.phase2;

import genericTraining.vo.RayVo;
import genericTraining.vo.RayVoHs;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Example1Test {

    /** Test main. */
    public static void main(String[] args) {
        // Test data.
        RayVo vo = generateVo();

        Example1 e1 = new Example1();
        RayVoHs voHs = new RayVoHs();
        e1.turn1(voHs, vo);
        e1.turn2(voHs, vo);

        RayVoHs resultVo1 = e1.turn3(RayVoHs.class, vo);
        RayVoHs resultVo2 = e1.turn4(RayVoHs.class, vo);

        // Example1當作工具類別使用。
        RayVoHs resultVo3 = Example1.turn5(RayVoHs.class, vo);

        // 特別介紹，用此方式可以映出物件中所有屬性和屬性的值。
        // ToStringStyle 有很多種，可以自己換看看。
        System.out.println(ToStringBuilder.reflectionToString(resultVo1, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(resultVo2, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ToStringBuilder.reflectionToString(resultVo3, ToStringStyle.MULTI_LINE_STYLE));

    }

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
