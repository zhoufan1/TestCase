package foundation.thread;

/**
 * @author:zhoufan
 * @date :2018-01-09 18:05
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    private void deadLock(){
        Thread thread = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        },"thread");

        Thread thread1 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }
        },"thread1");
        thread.start();
        thread1.start();
    }

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();

    }


}
