package shen.training;

import java.lang.reflect.Field;
import java.util.Date;

/**
 *
 */
public class GenerateSpecialWordRepace {

    private Integer storeNo;
    private Date actDateFrom;
    private Date actDateTo;
    private Date effDateFrom;
    private Date effDateTo;
    private String storeFlag;
    private String message;
    private String updId;
    private Date updDate;

    public static void main(String[] args) {
        final GenerateSpecialWordRepace vo = new GenerateSpecialWordRepace();

        for (Field f : vo.getClass().getDeclaredFields()) {
            final StringBuilder str = new StringBuilder();
            str.append("this.");
            str.append(f.getName());
            str.append(" = FormatHelper.repacePropertiesSpecialWord(this.");
            str.append(f.getName());
            str.append(");");

            System.out.println(str);
        }
    }

}
