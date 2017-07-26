package foundation;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhoufan on 2017/7/11.
 */
public class Test1 {
    @Test
    public void numberTest() {
        byte a = 1;

        byte b = 1;

        a = (byte) (a + b);

        a += b;
        System.out.println(a);

        System.out.println(Double.MAX_VALUE);

        System.out.println(Long.MAX_VALUE < Double.MAX_VALUE);
        Object aRef = new Object();
        SoftReference aSoftRef = new SoftReference(aRef);

        WeakHashMap weakHashMap = new WeakHashMap();

    }


    @Test
    public void runTimeTest() {
        Runtime runtime = Runtime.getRuntime();
        long l = runtime.freeMemory() / 1024 / 1024;
        System.out.println(l);
        System.out.println(runtime.totalMemory() / 1024 / 1024);
        System.out.println(runtime.maxMemory() / 1024 / 1024);
    }


    @Test
    public void hashTest() {
        int abc = Objects.hash("abc");
        int abc1 = Objects.hash("abc1");
        System.out.println(abc + "\t" + abc1);
        System.out.println(abc ^ abc1);
    }

    @Test
    public void finalTest() {

        System.out.println(s);
    }


    static public final HashMap s = new HashMap(10);


    ArrayBlockingQueue<Integer> queue  = new ArrayBlockingQueue<Integer>(50);


    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>(Comparator.comparingInt(value -> value.compareTo(value)));
        set.add(3);
        set.add(0);
        set.add(1);
        System.out.println(set);
    }

}
