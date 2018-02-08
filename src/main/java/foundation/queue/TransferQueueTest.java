package foundation.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import sun.rmi.runtime.RuntimeUtil;

/**
 * @author:zhoufan
 * @date :2018-02-05 17:07
 */
public class TransferQueueTest {

    @Test
    public void test1() throws InterruptedException {
        TransferQueue<Appointment<Pet>> linkedBlockingQueue = new LinkedTransferQueue<>();

        for (int i = 0; i < 100; i++) {
            Dog dog = new Dog("dog");
            Cat cat = new Cat("cat");
            Appointment appointment = new Appointment(dog);
            Appointment catApp = new Appointment(cat);
            linkedBlockingQueue.put(appointment);
            linkedBlockingQueue.put(catApp);
            Veterinarian veterinarian = new Veterinarian(linkedBlockingQueue, RandomUtils.nextInt(100, 1000));
            Veterinarian veterinarian1 = new Veterinarian(linkedBlockingQueue, RandomUtils.nextInt(100, 1000));
            veterinarian.start();
            veterinarian1.start();
        }
    }

    @Test
    public void testSchedule() throws InterruptedException {
        TransferQueue<Appointment<Pet>> linkedBlockingQueue = new LinkedTransferQueue<>();
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(10);

        Dog dog = new Dog("dog");
        Cat cat = new Cat("cat");
        Appointment appointment = new Appointment(dog);
        Appointment catApp = new Appointment(cat);
        linkedBlockingQueue.put(appointment);
        linkedBlockingQueue.put(catApp);
        Veterinarian veterinarian = new Veterinarian(linkedBlockingQueue, RandomUtils.nextInt(100, 1000));
        scheduled.scheduleAtFixedRate(veterinarian, 10, 10, TimeUnit.MICROSECONDS);
        scheduled.awaitTermination(10, TimeUnit.SECONDS);
        scheduled.shutdown();

    }


    public abstract class Pet {
        protected String name;

        public Pet(String name) {
            this.name = name;
        }

        protected abstract void examine();
    }

    class Dog extends Pet {

        public Dog(String name) {
            super(name);
        }

        @Override
        protected void examine() {
            System.out.println("dog  checkIng ...");
        }
    }

    class Cat extends Pet {

        public Cat(String name) {
            super(name);
        }

        @Override
        protected void examine() {
            System.out.println("cat  checkIng ...");
        }
    }

    class Appointment<T> {
        private final T toBeSeen;

        public T getPatient() {
            return toBeSeen;
        }

        public Appointment(T toBeSeen) {
            this.toBeSeen = toBeSeen;
        }
    }

    class Veterinarian extends Thread {
        protected final BlockingQueue<Appointment<Pet>> queue;

        protected String text = "";

        protected final int restTime;

        private boolean shutdown = false;

        public Veterinarian(BlockingQueue<Appointment<Pet>> queue, int pause) {
            this.queue = queue;
            this.restTime = pause;
        }

        public synchronized void shutdown() {
            shutdown = true;
        }

        @Override
        public void run() {
            while (!shutdown) {
                seePatient();
                try {
                    Thread.sleep(restTime);
                } catch (Exception e) {
                    shutdown = true;
                }
            }
            super.run();
        }

        public void seePatient() {
            try {
                Appointment<Pet> take = queue.take();
                Pet patient = take.getPatient();
                patient.examine();
            } catch (InterruptedException e) {
                shutdown = true;
            }
        }
    }


}
