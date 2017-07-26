package thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zhoufan on 2017/7/25.
 */
public class ThreadTest {

    public final Integer CAPACITY = 10;

    public ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(CAPACITY);

    public Object lock = new Object();


    @Test
    public void testArrayQueue() {

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
                Thread.sleep(300000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            get.interrupt();
            put.interrupt();
        });
        interripted.start();
    }

}
