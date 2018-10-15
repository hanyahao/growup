package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyThread4 implements Callable {
    @Override
    public Object call() throws Exception {
        long sum = 0;
        for (int i = 0; i < 9000; i++) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        MyThread4 thread4 = new MyThread4();
        FutureTask<Integer> result = new FutureTask<Integer>(thread4);
        new Thread(result).start();
    }
}
