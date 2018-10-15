package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ArrayListThread  {
    private static Vector<Integer> list = new Vector<Integer>();

    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i < 100000; i++) {
                    list.add(i);
                }
                System.out.println("thread1执行完毕");
            }
        });
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i < 100000; i++) {
                    list.add(i);
                }
                System.out.println("thread2执行完毕");

            }
        });
        thread2.start();

        Thread.sleep(1000);
        System.out.println(list.size());
        System.out.println("主线程执行完毕");
    }
}
