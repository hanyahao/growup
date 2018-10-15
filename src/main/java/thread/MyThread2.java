package thread;

public class MyThread2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("test");
        }
    }

    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();
    }
}
