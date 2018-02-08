package foundation.thread;

import java.util.concurrent.ArrayBlockingQueue;
import org.junit.Test;

/**
 * Created by zhoufan on 2017/7/25.
 */
public class ThreadTest {

    public final Integer CAPACITY = 10;

    public ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(CAPACITY);

    public Object lock = new Object();


    @Test
    public void testArrayQueue() throws InterruptedException {

        Thread put = new Thread(() -> {
            while (true) {
                try {
                    queue.put(1);
                    if (queue.size() == CAPACITY) {
                        Thread.sleep(1000);
                    }
                    System.out.println("put");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        put.start();


        Thread get = new Thread(() -> {
            while (true) {
                try {
                    if (queue.isEmpty()) {
                        Thread.sleep(500);
                    }
                    System.out.println("get" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        get.start();

        Thread interripted = new Thread(() -> {
            try {
                Thread.sleep(300000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            get.interrupt();
            put.interrupt();
        });
        interripted.start();
        put.join();
        get.join();
        interripted.join();
    }


    static volatile int x = 0, y = 0;
    static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread at = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread bt = new Thread(() -> {
            b = 1;
            y = a;
        });
        at.start();
        bt.start();
        at.join();
        bt.join();
        System.out.println("(” x = "+x +" + " + " y = "+ y+"“)");
    }

    @Test
    public void test2(){
        double r = 2.3d;
        double pi =3.1415926;
        double area =  r * r * pi;
        System.out.println(area);
    }

}
