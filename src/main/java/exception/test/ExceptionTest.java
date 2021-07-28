package exception.test;

public class ExceptionTest {

    public static void main(String[] args) {
        testErrorMssg();
    }

    private static void testErrorMssg() {
        try {
            if (true) {
                throw new Exception("[ut] test...");
            }
        } catch (Exception e) {
            final StackTraceElement elements[] = e.getStackTrace();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0, n = elements.length; i < n; i++) {
                sb.append(elements[i].getFileName()
                        + ":" + elements[i].getLineNumber()
                        + ">> "
                        + elements[i].getMethodName() + "()");
            }

            System.out.println(sb.toString());
            // ExceptionTest.java:12>> testErrorMssg()ExceptionTest.java:6>> main()
        }
    }
}
