package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ThreadHashMap {
    public static void main(String[] args) throws InterruptedException {
        final Map<String, String> map = new HashMap<String, String>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    },"thread:"+i).start();
                }
            }
        });

        thread.start();
        thread.join();
    }
}
