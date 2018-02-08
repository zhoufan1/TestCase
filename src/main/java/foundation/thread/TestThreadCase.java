package foundation.thread;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import javafx.util.Pair;
import org.junit.Test;

/**
 * @author:zhoufan
 * @date :2018-02-05 15:04
 */
public class TestThreadCase {

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch firstLatch = new CountDownLatch(1);
        final CountDownLatch secondLatch = new CountDownLatch(1);
        final Map<String, String> pair = new HashMap<>();
        pair.put("z", "hello");
        final Map<String, String> pair1 = new HashMap<>();
        pair1.put("h", "hello2");
        final CopyOnWriteArrayList<Map<String, String>> l = new CopyOnWriteArrayList<>();
        l.add(pair);
        l.add(pair1);
        ReentrantLock lock = new ReentrantLock();
        final MicroBlogTimeLine m = new MicroBlogTimeLine(l, lock, "xiaodao");
        final MicroBlogTimeLine m1 = new MicroBlogTimeLine(l, lock, "xiaodao2");

        Thread thread = new Thread(() -> {
            pair.put("111", "@22");
            l.add(pair);
            m.prep();
            firstLatch.countDown();
            try {
                secondLatch.await();
            } catch (InterruptedException e) {
            }
            m.printTimeLine();
        });
        Thread thread1 = new Thread(() -> {
            try {
                firstLatch.await();
                pair1.put("222", "@333");
                l.add(pair);
                m1.prep();
                secondLatch.countDown();
            } catch (InterruptedException e) {
            }
            m1.printTimeLine();
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

    }

    class MicroBlogTimeLine {


        private final CopyOnWriteArrayList<Map<String, String>> u;
        private final ReentrantLock lock;
        private final String name;
        private Iterator<Map<String, String>> it;

        public MicroBlogTimeLine(CopyOnWriteArrayList<Map<String, String>> u, ReentrantLock lock, String name) {
            this.u = u;
            this.lock = lock;
            this.name = name;
        }

        public void add(Pair<String, String> u) {

        }

        public void prep() {
            it = u.iterator();
        }

        public void printTimeLine() {
            lock.lock();
            try {
                if (it != null) {
                    System.out.println(name + ":");
                    while (it.hasNext()) {
                        Map<String, String> next = it.next();
                        System.out.println(JSON.toJSONString(next));
                    }
                    System.out.println();
                }
            } finally {
                lock.unlock();
            }
        }

    }
}
