package foundation.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:zhoufan
 * @date :2018-01-11 15:16
 */

public class ConcurrentTest {
    private AtomicLong count = new AtomicLong();

    public  long getNext(){
        return count.getAndAdd(1);
    }

    public static void main(String[] args) {
        ConcurrentTest test = new ConcurrentTest();
        Demo demo = new Demo();
        new Thread(() -> System.out.println("thread1:   \t"+test.getNext()),"thread1").start();
        new Thread(() -> System.out.println("thread2:   \t"+test.getNext()),"thread2").start();
        new Thread(() -> System.out.println("thread3:   \t"+test.getNext()),"thread3").start();
        new Thread(() -> System.out.println("thread4:   \t"+test.getNext()),"thread4").start();
        new Thread(() -> System.out.println("thread5:   \t"+test.getNext()),"thread5").start();
    }
}

class  Demo{
    private int i ;

    public  int getNext(){
        return i++;
    }
}