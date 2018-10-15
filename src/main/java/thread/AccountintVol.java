package thread;

public class AccountintVol implements Runnable {
    private volatile int i = 0;
    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            addI();
        }
        System.out.println("111");
    }

     public void addI() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        AccountintVol ac = new AccountintVol();
        Thread thread1 = new Thread(ac);
        Thread thread2 = new Thread(ac);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        System.out.println(ac.i);
        System.out.println("222");
    }
}
