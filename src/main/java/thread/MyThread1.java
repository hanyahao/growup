package thread;

public class MyThread1 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("test ------------------");
        }
    }

    public static void main(String[] args) {
        MyThread1 thread1 = new MyThread1();
        thread1.start();
        System.out.println();
       // Thread thread = new Thread(thread1);
    }
}
