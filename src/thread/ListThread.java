package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoufan on 2017/6/9.
 */
public class ListThread {
    public static final List list = new ArrayList();

    public static void add(String number) {
        list.add(number);
    }

    public static void main(String[] args) {

       /* int i=5;
        int s=(i++)+(++i)+(i--)+(--i);
        System.out.println(s);*/

      /*  run();
        System.out.println(JSON.toJSONString(list));
        list.clear();
        run();
        System.out.println(JSON.toJSONString(list));
        list.clear();
        run();
        System.out.println(JSON.toJSONString(list));*/
    /*    try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        System.out.println(list.size());

    }

    private static void run() {
        new Thread(() -> {
            ListThread.add("3");
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ListThread.add("4");
        }).start();
    }
}
