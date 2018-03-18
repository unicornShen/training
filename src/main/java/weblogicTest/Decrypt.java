//package weblogicTest;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Properties;
//
//import weblogic.security.internal.SerializedSystemIni;
//import weblogic.security.internal.encryption.ClearOrEncryptedService;
//
//// import => C:\Oracle\Middleware\wlserver_10.3\server\lib\weblogic.jar
//public class Decrypt {
//
//    static weblogic.security.internal.encryption.EncryptionService es = null;
//
//    static ClearOrEncryptedService ces = null;
//
//    /**
//     * example: java -classpath
//     * 
//     * D:\Oracle\Middleware10\wlserver_10.3\server\lib\weblogic.jar;.;.. Decrypt
//     * 
//     * D:\\Oracle\\Middleware10\\user_projects\\domains\\base_domain
//     * 
//     */
//    public static void main(String args[]) {
//
//        String s = "C:\\Oracle\\Middleware\\user_projects\\domains\\base_domain";
//
//        //        if (args == null || args.length == 0)
//        //            System.err.println("input params [0] .... ");
//        //
//        //        else {
//        //            s = args[0];
//        //        }
//
//        if (!new File(s).exists()) {
//            System.err.println("weblogic root not exists ");
//        }
//
//        Properties dbProperties = new Properties();
//        File keyFile = new File(s + "\\servers\\AdminServer\\security\\boot.properties");
//        //        File keyFile = new File("C:\\Oracle\\Middleware\\user_projects\\domains\\base_domain\\servers\\AdminServer\\security\\boot.properties");
//
//        if (!keyFile.exists()) {
//            System.err.println("boot.properties not found ");
//            return;
//        }
//
//        try {
//            dbProperties.load(new FileInputStream(keyFile));
//
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            System.err.println("loading boot.properties error ");
//            return;
//        }
//
//        File wlsRoot = new File(s + "\\security");
//
//        if (!wlsRoot.exists()) {
//            System.err.println("security folder not exists ");
//
//        } else {
//            System.setProperty("weblogic.RootDirectory", wlsRoot.getAbsolutePath());
//            es = SerializedSystemIni.getExistingEncryptionService();
//
//            if (es == null) {
//                System.err.println("Unable to initialize encryption service");
//                return;
//            }
//
//            ces = new ClearOrEncryptedService(es);
//
//            System.out.println("\nDecrypted userName is:" + ces.decrypt(dbProperties.getProperty("username")));
//            System.out.println("\nDecrypted userName is:" + ces.decrypt(dbProperties.getProperty("password")));
//        }
//
//    }
//
//}
