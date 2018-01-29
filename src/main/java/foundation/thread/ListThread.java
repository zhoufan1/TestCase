package foundation.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoufan on 2017/6/9.
 */
public class ListThread {

    private volatile AtomicInteger defaultNum = new AtomicInteger(0);

    private volatile Integer v = 0;
    public static final List list = new ArrayList();

    public static void add(String number) {
        list.add(number);
    }

    public void test() {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
//                synchronized (list) {

                defaultNum.addAndGet(1);
//                    System.out.println("thread：" + defaultNum);
//                }
            }, "thread" + i);
            thread.start();
            System.out.println(thread.getName() + "::::\t" + v);
   /*         try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
        }
        System.out.println("count:" + defaultNum);
//        new ThreadPoolExecutor();

       /* new Thread(() -> {
            defaultNum = defaultNum + 2;
                System.out.println("thread1："+defaultNum);
        }).start();

        System.out.println(defaultNum);*/
    }

    public static void main(String[] args) {
        new ListThread().test();


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

  /*  private static void run() {
        new Thread(() -> {
            ListThread.add("3");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ListThread.add("4");
        }).start();
    }*/
}
