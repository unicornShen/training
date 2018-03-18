package classForNameTest;

/**
 * <pre>
 * Class說明。
 * </pre>
 *
 * @since 2018年3月18日
 * @author Unicorn
 * @version <ul>
 *            <li>2018年3月18日,Unicorn,new
 *          </ul>
 */
public class ClassForNameTest {

    public static void main(String[] args) {
        try {
            Class<IHandler> cls = (Class<IHandler>) Class.forName("classForNameTest.HanlerImpl");

            final String psCode = cls.newInstance().processing();
            System.out.println(psCode);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(ClassForNameTest.class.getName());
    }

}
