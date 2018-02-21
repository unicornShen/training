package genericTraining.phase2;

import genericTraining.vo.RayVo;
import genericTraining.vo.RayVoHs;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Example2Test {

    /** Test main. */
    public static void main(String[] args) {
        // Test data.
        RayVo vo = generateVo();

        // This target VO maybe is query result or another method get.
        RayVoHs targetVo = new RayVoHs();

        //
        // 這邊剛好是RayVoHs 有 implements IExample, 如果此VO沒辦法implements 那又該如何做自定轉換?????  --> please look Example3Test.
        // P.S. 沒辦法implements的情況有可能是，此VO維護層級不在你身上，或統一控管不得修改之類...
        // 當然，你也可以自己做一個物件，然後去implements IExamle.
        // 
        RayVoHs hs = Example2.turnByConvert(targetVo, vo);
        System.out.println("[U]Trace = " + ToStringBuilder.reflectionToString(hs, ToStringStyle.MULTI_LINE_STYLE));

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
