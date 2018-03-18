package readingTraining.reading1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Item45Trace1 {

    public static void main(String[] s) {

        List<String> list = new ArrayList<String>();
        Iterator<String> i = list.iterator();
        while (i.hasNext()) {
            doSomething(i.next());
        }

        List<String> list2 = new ArrayList<String>();
        Iterator<String> i2 = list2.iterator();
        while (i.hasNext()) {
            doSomething(i2.next());
        }

        // ----------------------------------------

        for (Iterator<String> j = list.iterator(); j.hasNext();) {
            doSomething(j.next());
        }

//        for (Iterator<String> j2 = list.iterator(); j.hasNext();) {
//            doSomething(j2.next());
//        }

        // ----------------------------------------

        for (int k = 0, n = expensiveComputation(); k < n; k++) {
            doSomething(k);
        }

        
        String[] array = {};
        
        for (int t = 0; t < array.length ; t++) {
            doSomething(array[t]);
        }

    }

    public static void doSomething(Object o) {
        // ...
    }

    public static int expensiveComputation() {
        return 7;
    }
}
