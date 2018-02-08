package foundation.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

/**
 * @author:zhoufan
 * @date :2018-02-02 14:29
 */
public class BlockingQueueTest {
    AtomicInteger coutn = new AtomicInteger(1);
    final int size = 10;

    @Test
    public void getAndSetTest() throws InterruptedException {
        LinkedBlockingQueue<Long> integers = new LinkedBlockingQueue<>(size);
        LinkedBlockingQueue<Runnable> arrayBlockingQueue = new LinkedBlockingQueue<>(100);

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(50, 100, 10,
                TimeUnit.SECONDS, arrayBlockingQueue, (r, executor) -> System.out.println("当前队列数：" + executor.getActiveCount() + ",排队等待..."));
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Product(integers, size));
            executorService.execute(new Consumer(integers));
        }

        do {
            System.out.println("未执行完，等待：" + executorService.getActiveCount());
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } while (executorService.isTerminated());
        executorService.shutdown();
    }
}

class Consumer implements Runnable {

    public Consumer(LinkedBlockingQueue<Long> queue) {
        this.queue = queue;
    }

    private boolean isRunning = true;
    private LinkedBlockingQueue<Long> queue = null;

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                boolean empty = queue.isEmpty();
                try {
                    if (empty) {
                        System.out.println("当前没有消费,等待生产。。。");
                    }
                    System.out.println("消费者: " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                isRunning = false;
            }
        }

    }

    public void stop() {
        isRunning = false;
    }
}

class Product implements Runnable {

    public Product(LinkedBlockingQueue<Long> queue, Integer size) {
        this.queue = queue;
        this.size = size;
    }

    private boolean isRunning = true;
    private int size;
    private LinkedBlockingQueue<Long> queue = null;

    @Override
    public void run() {
        while (isRunning) {
            if (queue.size() == size) {
                System.out.println("已经成产满，等待消费。");
            }
            try {
                long e = System.currentTimeMillis();
                System.out.println("product :" + e);
                queue.put(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stop() {
        isRunning = false;
    }
}
