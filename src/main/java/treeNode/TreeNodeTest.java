package treeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 */
public class TreeNodeTest {

    public static void main(String[] args) {
        //----------------------------------
        //  801
        //   |
        //   |---801.1
        //   |    |
        //   |    |----801.1.1
        //   |    |
        //   |    |----801.1.2
        //   |
        //   |
        //   |---801.2
        //----------------------------------

        final List<CheckItmeProblemVo> dataList = new ArrayList<>();
        final CheckItmeProblemVo vo2 = new CheckItmeProblemVo();
        vo2.setCheckSeq("801");
        vo2.setParent("800");
        vo2.setCheckOrder("5");
        vo2.setModifierName(" ");
        vo2.setCheckItem("");
        vo2.setCheckType("");
        vo2.setCheckProblem("");
        vo2.setAnswer("src");
        vo2.setCheckDate("");
        vo2.setSrcTable("problem");
        vo2.setSrcSeq("99999999");
        vo2.setStatus("S");
        dataList.add(vo2);

        final CheckItmeProblemVo vo = new CheckItmeProblemVo();
        vo.setCheckSeq("1001");
        vo.setParent("1000");
        vo.setCheckOrder("2");
        vo.setModifierName("Z00007255<br/>江家玲");
        vo.setCheckItem("");
        vo.setCheckType("訪談理專");
        vo.setCheckProblem("確認理專是否有不當銷售行為.");
        vo.setAnswer("src");
        vo.setCheckDate("2017/10/09");
        vo.setSrcTable("problem");
        vo.setSrcSeq("0");
        vo.setStatus("S");
        dataList.add(vo);

        final CheckItmeProblemVo vo1 = new CheckItmeProblemVo();
        vo1.setCheckSeq("1001");
        vo1.setParent("1001");
        vo1.setCheckOrder("1001,001");
        vo1.setModifierName(" ");
        vo1.setCheckItem("");
        vo1.setCheckType("");
        vo1.setCheckProblem("");
        vo1.setAnswer("src");
        vo1.setCheckDate("");
        vo1.setSrcTable("feedback");
        vo1.setSrcSeq("99999999");
        vo1.setStatus("S");
        dataList.add(vo1);

        addNode(dataList, vo);
        addNode(dataList, vo1);
        //        addNode(dataList, vo1);
        addNode(dataList, vo);

        for (CheckItmeProblemVo data : dataList) {
            System.out.println(ToStringBuilder.reflectionToString(data, ToStringStyle.MULTI_LINE_STYLE));
        }
    }

    // TODO
    private static void addNode(final List<CheckItmeProblemVo> dataList, final CheckItmeProblemVo srcProblemVo) {

        String parentNode = "";
        if (StringUtils.equalsIgnoreCase(srcProblemVo.getSrcTable(), "problem")) {
            parentNode = srcProblemVo.getCheckSeq();

        } else {
            parentNode = srcProblemVo.getCheckOrder();
        }

        int nodeSize = 1;
        for (CheckItmeProblemVo data : dataList) {

            String parent = "";
            if (StringUtils.equalsIgnoreCase(srcProblemVo.getSrcTable(), "problem")) {
                parent = data.getParent();

            } else {
                parent = getRelParent(srcProblemVo.getCheckOrder());
            }
            System.out.println("rel parent = " + parent);

            if (StringUtils.contains(parent, parentNode)) {
                String[] parentArray = data.getParent().split(",");
                String[] parentNodeArray = parentNode.split(",");

                System.out.println("parentArray.length = " + parentArray.length);
                System.out.println("parentNodeArray.length = " + parentNodeArray.length);

                if (parentArray.length == parentNodeArray.length) {
                    nodeSize++;
                }
            }
        }

        final CheckItmeProblemVo node = new CheckItmeProblemVo();
        node.setCheckSeq(srcProblemVo.getCheckSeq());
        node.setParent(parentNode);
        node.setCheckOrder(parentNode + "," + StringUtils.leftPad(ObjectUtils.toString(nodeSize), 3, "0"));
        node.setModifierName(" ");
        node.setCheckProblem("");
        node.setAnswer("");
        node.setCheckDate("");
        node.setSrcTable("feedback");
        node.setStatus("A");

        dataList.add(node);
    }

    private static String getRelParent(final String checkOrder) {
        String[] array = checkOrder.split(",");
        String[] destList = new String[array.length - 1];
        System.arraycopy(array, 0, destList, 0, array.length - 1);

        return StringUtils.join(destList, ",");
    }

}
