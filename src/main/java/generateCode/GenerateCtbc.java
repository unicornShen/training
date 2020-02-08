package generateCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class GenerateCtbc {

    public static void genModel(final Map<String, String> map) {

        final StringBuilder str = new StringBuilder();
        str.append("@Entity");
        str.append(System.lineSeparator());
        str.append("@Table(name = \"\")");
        str.append(System.lineSeparator());
        str.append("@EmbeddedId");
        str.append(System.lineSeparator());
        System.out.println(str);

        for (String field : map.keySet()) {
            System.out.println(genField(field, map.get(field)));
        }

    }

    private static String genField(String fieldName, String comment) {
        StringBuilder reStr = new StringBuilder();

        reStr.append("/** " + comment + ". */");
        reStr.append(System.lineSeparator());
        //        reStr.append("@TableColumnName(\"" + fieldName.toLowerCase() + "\")");
        //        reStr.append("\n");
        //        reStr.append("@CsvColumn");
        //        reStr.append("\n");
        reStr.append("@Column(name = \"" + fieldName + "\")"); // hibernate
        reStr.append(System.lineSeparator());
        reStr.append("private String " + GenerateAll.upperFirstCharByStr(fieldName.toLowerCase(), "_") + ";");
        reStr.append(System.lineSeparator());

        return reStr.toString();
    }

    public static void genDataObject(final Map<String, String> map) {
        final List<String> keyList = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            keyList.add(entry.getKey());
        }

        final String key = keyList.get(0);
        final String className = TurnTool.upFirstStr(TurnTool.upperFirstCharByStr(key, "_"));
        System.out.println(className + "DO");

        for (String str : map.keySet()) {
            StringBuilder reStr = new StringBuilder();
            reStr.append("/** " + map.get(str) + ". */");
            reStr.append(System.lineSeparator());
            reStr.append("private String " + str + ";");
            reStr.append(System.lineSeparator());
            System.out.println(reStr);
        }

    }

    public static void genDao(final Map<String, String> map) {

        final List<String> keyList = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            keyList.add(entry.getKey());
        }

        final String key = keyList.get(0);
        final String className = TurnTool.upFirstStr(TurnTool.upperFirstCharByStr(key.toLowerCase(), "_"));

        final StringBuilder str = new StringBuilder();
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * " + map.get(key) + " DAO.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" public interface " + className + "Dao {");
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * Find by PK.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" public " + className + "VO findByPk(" + className + "PK pk);");

        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * Delete by PK");
        str.append(System.lineSeparator());
        str.append("  * ");
        str.append(System.lineSeparator());
        str.append("  * @param pk");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" public void deleteByPk(" + className + "PK pk);");
        str.append(System.lineSeparator());

        str.append(joinStr("" //
                , " /**" //
                , "  * Save or update data." //
                , "  * " //
                , "  * @param updateData {@link " + className + "DO}" //
                , "  * @param userId 使用者ID." //
                , "  * @param date 現在時間." //
                , "  * @return {@link " + className + "VO}" //
                , "  */" //
                , " public " + className + "VO saveOrUpdate(" + className + "DO updateData, String userId, Date date);" //
        ));

        str.append(System.lineSeparator());
        str.append(" }");

        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * " + map.get(key) + " DAO.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append("@Repository");
        str.append(System.lineSeparator());
        str.append(" public class " + className + "DaoImpl extends NsaDbDaoImpl<" + className + "VO, " + className + "PK> implements " + className + "Dao {");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append(" /** Logger. */");
        str.append(System.lineSeparator());
        str.append(" private static final Logger LOGGER = LoggerFactory.getLogger(" + className + "DaoImpl.class);");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * Find by PK.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" @Override                                                        ");
        str.append(System.lineSeparator());
        str.append(" public " + className + "VO findByPk(final " + className + "PK pk) {          ");
        str.append(System.lineSeparator());
        str.append("     LOGGER.debug(\"[d] " + className + "DaoImpl.findByPk()\");           ");
        str.append(System.lineSeparator());
        str.append("                                                                  ");
        str.append(System.lineSeparator());
        str.append("     " + className + "VO data = null;                                   ");
        str.append(System.lineSeparator());
        str.append("     try {                                                        ");
        str.append(System.lineSeparator());
        str.append("         data = this.findByPKey(pk);                              ");
        str.append(System.lineSeparator());
        str.append("                                                                  ");
        str.append(System.lineSeparator());
        str.append("     } catch (Exception e) {                                      ");
        str.append(System.lineSeparator());
        str.append("         throw new RuntimeException(e);                           ");
        str.append(System.lineSeparator());
        str.append("     }                                                            ");
        str.append(System.lineSeparator());
        str.append("                                                                  ");
        str.append(System.lineSeparator());
        str.append("     return data;                                                 ");
        str.append(System.lineSeparator());
        str.append(" }                                                                ");
        str.append(System.lineSeparator());
        str.append("                                                                  ");
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * Delete by PK");
        str.append(System.lineSeparator());
        str.append("  */                                                              ");
        str.append(System.lineSeparator());
        str.append(" @Override                                                        ");
        str.append(System.lineSeparator());
        str.append(" public void deleteByPk(final " + className + "PK pk) {      ");
        str.append(System.lineSeparator());
        str.append("     LOGGER.debug(\"[d] " + className + "Impl.deleteByPk()\");         ");
        str.append(System.lineSeparator());
        str.append("                                                                  ");
        str.append(System.lineSeparator());
        str.append("     try {                                                        ");
        str.append(System.lineSeparator());
        str.append("         final " + className + "VO vo = this.findByPKey(pk); ");
        str.append(System.lineSeparator());
        str.append("         if (vo != null) {                                        ");
        str.append(System.lineSeparator());
        str.append("             this.delete(vo);                                     ");
        str.append(System.lineSeparator());
        str.append("         }                                                        ");
        str.append(System.lineSeparator());
        str.append("     } catch (Exception e) {                                      ");
        str.append(System.lineSeparator());
        str.append("         throw new RuntimeException(e);                           ");
        str.append(System.lineSeparator());
        str.append("     }                                                            ");
        str.append(System.lineSeparator());
        str.append(" }                                                                ");
        str.append(System.lineSeparator());

        str.append(joinStr("" //
                , " /**"//
                , "  * Save or update data."//
                , "  * "//
                , "  * @param updateData {@link " + className + "DO}"//
                , "  * @param userId 使用者ID."//
                , "  * @param date 現在時間."//
                , "  * @return {@link " + className + "VO}"//
                , "  */"//
                , " @Override                                                                                                "//
                , " public " + className + "VO saveOrUpdate(final " + className + "DO updateData, final String userId, final Date date) {"//
                , "     LOGGER.debug(\"[d] " + className + "DaoImpl.saveOrUpdate()\");                                          "//
                , "                                                                                                          "//
                , "     try {                                                                                                "//
                , "         " + className + "VO vo = this.findByPKey(new " + className + "PK(updateData.get" + className + "Seq()));        "//
                , "         if (vo == null) {                                                                                "//
                , "             vo = ConvertUtil.objToVo(updateData, " + className + "VO.class);                                      "//
                , "             vo.setPk(new " + className + "PK(ScpUtil.getUUID()));                                           "//
                , "             vo.setCreator(userId);                                                                       "//
                , "             vo.setCreatetime(date);                                                                      "//
                , "                                                                                                          "//
                , "         } else {                                                                                         "//
                , "             ConvertUtil.setModifyValue(vo, updateData);                                                        "//
                , "         }                                                                                                "//
                , "                                                                                                          "//
                , "         vo.setModifier(userId);                                                                          "//
                , "         vo.setLastupdate(date);                                                                          "//
                , "                                                                                                          "//
                , "         return this.update(vo);                                                                          "//
                , "                                                                                                          "//
                , "     } catch (Exception e) {                                                                              "//
                , "         throw new RuntimeException(e);                                                                   "//
                , "     }                                                                                                    "//
                , " }                                                                                                        "//
        ));
        str.append(System.lineSeparator());
        str.append(" }");

        System.out.println(str);
    }

    private static String joinStr(String... str) {
        return StringUtils.join(str, System.lineSeparator());
    }
}
