package thread;

import utils.SignUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class HashMapThread {
    private static Hashtable<String, String> map = new Hashtable<String, String>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i =0; i < 1000000; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
                System.out.println("thread1执行完毕");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i =0; i < 1000000; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
                System.out.println("thread2执行完毕");

            }
        });
        thread2.start();
        Thread.sleep(10000);
        System.out.println(map.size());

    }
}
