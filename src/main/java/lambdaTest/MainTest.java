package lambdaTest;

/**
 * 
 */
public class MainTest {
    public static void main(String[] args) {
        String ans = method03("this string.", (str) -> {
            return str;
        });
        
        System.out.println(ans);
    }

    public static String method01() {
        return "01";
    }

    public static String method02(final String str) {
        return str;
    }

    public static String method03(final String str, final LlambdaInterface ld) {
        return ld.testMethod(str);
    }
}
