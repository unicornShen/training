package generateCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateMao {

    public static void genModel(final Map<String, String> map) {
        
        final StringBuilder str = new StringBuilder();
        str.append("@Entity");
        str.append(System.lineSeparator());
        str.append("@Table(name = \"\")");
        str.append(System.lineSeparator());
        str.append("@Id");
        str.append(System.lineSeparator());
        str.append("@GeneratedValue(strategy = GenerationType.IDENTITY)");
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

    public static void genRpy(final Map<String, String> map) {

        final List<String> keyList = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            keyList.add(entry.getKey());
        }

        final String key = keyList.get(0);
        final String className = TurnTool.upFirstStr(TurnTool.upperFirstCharByStr(key, "_"));

        final StringBuilder str = new StringBuilder();
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * " + map.get(key) + ": " + key + ".");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" public interface " + className + "Repository extends CrudRepository<" + className + ", Long> {");
        str.append(" ");
        str.append(" }");
        str.append(System.lineSeparator());

        System.out.println(str);
    }

    public static void genDao(final Map<String, String> map) {

        final List<String> keyList = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            keyList.add(entry.getKey());
        }

        final String key = keyList.get(0);
        final String className = TurnTool.upFirstStr(TurnTool.upperFirstCharByStr(key, "_"));

        final StringBuilder str = new StringBuilder();
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * " + map.get(key) + " DAO.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" public interface " + className + "Dao {");
        str.append(" ");
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
        str.append(" public class " + className + "DaoImpl  implements " + className + "Dao {");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append(" /** Logger. */");
        str.append(" private static final Logger LOGGER = LogManager.getLogger(" + className + "DaoImpl.class);");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append(" @PersistenceContext");
        str.append(System.lineSeparator());
        str.append(" private EntityManager em;");
        str.append(System.lineSeparator());
        str.append(" }");

        System.out.println(str);
    }

    public static void genService(final Map<String, String> map) {

        final List<String> keyList = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            keyList.add(entry.getKey());
        }

        final String key = keyList.get(0);
        final String fdName = TurnTool.upperFirstCharByStr(key, "_");
        final String className = TurnTool.upFirstStr(fdName);

        final StringBuilder str = new StringBuilder();
        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * " + map.get(key) + " Service.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append(" public interface " + className + "Service {");
        str.append(" ");
        str.append(" }");
        str.append(System.lineSeparator());

        str.append(System.lineSeparator());
        str.append(" /**");
        str.append(System.lineSeparator());
        str.append("  * " + map.get(key) + " Service.");
        str.append(System.lineSeparator());
        str.append("  */");
        str.append(System.lineSeparator());
        str.append("@Service");
        str.append(System.lineSeparator());
        str.append(" public class " + className + "ServiceImpl  implements " + className + "Service {");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());
        str.append(" /** Logger. */");
        str.append(System.lineSeparator());
        str.append(" private static final Logger LOGGER = LogManager.getLogger(" + className + "ServiceImpl.class);");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());

        str.append(" /** " + map.get(key) + " RPY. */");
        str.append(System.lineSeparator());
        str.append(" @Autowired");
        str.append(System.lineSeparator());
        str.append(" private " + className + "Repository rpy;");
        str.append(System.lineSeparator());
        str.append(System.lineSeparator());

        str.append(" /** " + map.get(key) + " DAO. */");
        str.append(System.lineSeparator());
        str.append(" @Autowired");
        str.append(System.lineSeparator());
        str.append(" private " + className + "Dao dao;");
        str.append(System.lineSeparator());
        str.append(" }");

        System.out.println(str);
    }

}
