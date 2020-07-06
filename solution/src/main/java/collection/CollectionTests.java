package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by god on 2019/5/15.
 */
public class CollectionTests {


    public static void main(String[] args){

        Integer i = 128;
        Integer j = 128;

        System.out.println(Integer.MAX_VALUE);
        System.out.println(i == j);
        System.out.println(i.equals(j));

        String a = "a;b;v;f;;;;;";
        String b = "aa.bc.v.f.";
        String[] arr = a.split(";");
        String[] arr2 = b.split("\\.");
        System.out.println(arr.length);
        System.out.println(arr2.length);

        StringBuffer buffer;
        StringBuilder bilder;

        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        List<String> sub =  list.subList(0,1);
        System.out.println(list);
        sub.set(0,"33");
        System.out.println(list);



    }

}
