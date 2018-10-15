package thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<100000; i++) {
                    lock.lock();
                    lock.lock();
                    count++;
                    lock.unlock();
                    lock.unlock();
                    lock.unlock();
                }
                System.out.println("thread1执行完毕");
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<100000; i++) {
                    lock.lock();
                    count++;
                    lock.unlock();
                }
                System.out.println("thread2执行完毕");
            }
        });
        thread2.start();
        Thread.sleep(1000);
        System.out.println(count);


    }
}
