package shen.training;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class TurnString {
    public static void main(String[] args) {

        turn("CK_ST_55_D_G_V");
    }

    public static final String turn(final String turnStr) {
        StringBuilder reStr = new StringBuilder();

        String[] arrayStr = StringUtils.split(turnStr, "_");

        for (String str : arrayStr) {
//            System.out.println(str);

            String[] splitStr = str.split("");
            for (int i = 0; i < splitStr.length; i++) {
//                System.out.println("splitStr[" + i + "] = " + splitStr[i]);
                if (i == 1) {
                    reStr.append(StringUtils.upperCase(splitStr[i]));
                } else {
                    reStr.append(StringUtils.lowerCase(splitStr[i]));
                }
            }
        }

        System.out.println("reStr = " + reStr);
        return reStr.toString();
    }

}
