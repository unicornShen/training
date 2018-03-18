package readingTraining.reading1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Item46Trace1 {

    enum Face {
        ONE, TWO, THREE, FOUR, FIVE, SIX
    }

    public static void main(String[] s) {
        case1();
        //case2();
        //case3();
    }

    public static void case1() {
        List<String> list = genList();

        for (String str : list) {
            System.out.println("str = " + str);
        }

        for (String str : list) {
            if (str.equals("b")) {
                list.remove(str);
            }
        }
        
        for(Iterator<String> i = list.iterator(); i.hasNext();){
            String str = i.next();
            if("a".equals(str)){
                i.remove();
            }
        }

    }

    public static void case2() {
        String[] strArray = genArray();
        for (String str : strArray) {
            // if strArray[3] then str="T" ...
        }

    }

    public static void case3() {
        Collection<Face> faces = Arrays.asList(Face.values());
        for (Iterator<Face> i = faces.iterator(); i.hasNext();) {
            for (Iterator<Face> j = faces.iterator(); j.hasNext();)
                System.out.println(i.next() + " " + j.next());
        }

        for (Iterator<Face> i = faces.iterator(); i.hasNext();) {
            Face suit = i.next();
            for (Iterator<Face> j = faces.iterator(); j.hasNext();)
                System.out.println(suit.name() + " " + j.next().name());
        }

        for (Face f : Face.values()) {
            for (Face ff : Face.values()) {
                System.out.println(f + " " + ff);
            }
        }
    }

    public static List<String> genList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        return list;
    }

    public static String[] genArray() {
        String[] str = { "a", "b", "c", "D", "E" };
        return str;
    }

    public static List<Vo> genVoList() {
        List<Vo> list = new ArrayList<Vo>();
        Vo v1 = new Vo();
        v1.setStr1("a");
        v1.setStr2("b");
        list.add(v1);

        Vo v2 = new Vo();
        v2.setStr1("z");
        v2.setStr2("x");
        list.add(v2);

        return list;
    }

}
