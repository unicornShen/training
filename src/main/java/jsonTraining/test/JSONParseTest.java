package jsonTraining.test;

import java.util.Iterator;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JSONParseTest {
    //    commons-beanutils.jar，
    //    commons-httpclient.jar，
    //    commons-lang.jar，
    //    ezmorph.jar，
    //    morph-1.0.1.jar

    //    http://www.docjar.com/
    //    http://json-lib.sourceforge.net/
    //    http://ezmorph.sourceforge.net/
    //    http://morph.sourceforge.net/

    public static void main(String[] args) {
        parseTest1();
        
        System.out.println(ProcessType.SUCCESS.getRd901T());
    }

    private static void parseTest1() {
        final String jsonFormatStr = "{a:3, b:7}";
        JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonFormatStr);

        for (Iterator<?> it = json.keys(); it.hasNext();) {
            String key = (String) it.next();
            System.out.println(String.format("key=[%s], value=[%s]", key, json.get(key)));

            System.out.println(json.containsKey(key));
            System.out.println(json.getInt(key));
            System.out.println("------------------------");
        }

    }

    /***/
    public enum ProcessType {
        SUCCESS("1", "{rd901T:'a123', rd903T:'b456', rd907T:'', rd908T:''}"), //
        FIAL("0", "{rd901T:'', rd903T:'', rd907T:'c789', rd908T:'u5566'}"), //
        ;
        final String code;

        private String rd901T;

        private String rd903T;

        private String rd907T;

        private String rd908T;

        private ProcessType(final String code, final String errorText) {
            this.code = code;
            
            JSONObject json = (JSONObject) JSONSerializer.toJSON(errorText);
            this.rd901T = json.getString("rd901T");
            this.rd903T = json.getString("rd903T");
            this.rd907T = json.getString("rd907T");
            this.rd908T = json.getString("rd908T");
        }

        public String getRd901T() {
            return this.rd901T;
        }

        public void setRd901T(String rd901t) {
            this.rd901T = rd901t;
        }

        public String getRd903T() {
            return this.rd903T;
        }

        public void setRd903T(String rd903t) {
            this.rd903T = rd903t;
        }

        public String getRd907T() {
            return this.rd907T;
        }

        public void setRd907T(String rd907t) {
            this.rd907T = rd907t;
        }

        public String getRd908T() {
            return this.rd908T;
        }

        public void setRd908T(String rd908t) {
            this.rd908T = rd908t;
        }

    }
}
