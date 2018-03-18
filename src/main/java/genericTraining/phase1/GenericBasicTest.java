package genericTraining.phase1;

import genericTraining.vo.RayVo;
import genericTraining.vo.UnicornVo;

import java.util.ArrayList;
import java.util.List;

public class GenericBasicTest {
    public static void main(String[] args) {
        // 有implements IVo.
        UnicornVo vo = new UnicornVo();
        List<UnicornVo> voList = new ArrayList<UnicornVo>();
        voList.add(vo);

        // 沒implements IVo.
        RayVo rVo = new RayVo();
        List<RayVo> rVoList = new ArrayList<RayVo>();
        rVoList.add(rVo);

        GenericBasic generic = new GenericBasic();

        // 測試.
        generic.test0(voList);
        generic.test1_1(voList);
        generic.test1_2(voList);
        generic.test1_3(voList);
        // generic.test1_3(rVoList); // Error because: method <? extends IVo> but, RayVo no implements IVo. 
        generic.test2_1(voList);
        generic.test2_2(voList, vo);
        generic.test2_3(voList);
        generic.test2_4(voList);
        // generic.test2_4(rVoList); // Error because: method <T extends IVo> but, RayVo no implements IVo.

    }

}
