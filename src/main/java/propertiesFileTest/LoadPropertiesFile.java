package propertiesFileTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 */
public class LoadPropertiesFile {

    /** 絕對路徑. */
    public static void AbsolutePath() {
        System.out.println("[AbsolutePath]");

        final String filename = "/workspace/Struts2Example/src/main/resources/config/database/properties/database.properties";
        final Properties prop = new Properties();

        try (InputStream input = new FileInputStream(filename)) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("jdbc.driverClassName"));
            System.out.println(prop.getProperty("jdbc.url"));
            System.out.println(prop.getProperty("jdbc.username"));
            System.out.println(prop.getProperty("jdbc.password"));

        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }

    /** 專案路徑. */
    public static void projectPath() {
        System.out.println("[projectPath]");

        final String filename = "AECD0001.properties";
        final Properties prop = new Properties();

        try (InputStream input = LoadPropertiesFile.class.getClassLoader().getResourceAsStream(filename);) {

            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            final Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("Key : " + key + ", Value : " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Test. */
    public static void main(String[] args) {
        AbsolutePath();
        projectPath();
    }
}
