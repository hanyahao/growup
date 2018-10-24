package thread.future;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class FutureData implements Data {
    private RealData realData = null;
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.getStackTrace();
            }
        }
        return realData.result;
    }


    static int[] arr = {1, 5, 68, 4, 5, 6, 7, 48, 145, 896};
    public static void main(String[] args) {
        Arrays.stream(arr).forEach((x) -> System.out.println(x));
    }
}
