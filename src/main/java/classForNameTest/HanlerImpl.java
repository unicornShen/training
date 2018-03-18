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
public class HanlerImpl implements IHandler {

    @Override
    public String processing() {
        System.out.println("[] processing()");
        return "success!";
    }
    
    

}
