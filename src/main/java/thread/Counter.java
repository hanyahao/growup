package thread;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<Thread>(600);
        for (int j = 0; j <100; j++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        counter.count();
                        counter.safeCount();
                    }
                }
            });
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());

    }


    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        while (true) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            System.out.println(i+"----"+suc);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    public void count() {
        i++;
    }

}
