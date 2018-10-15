package thread;

public class MyThreadt extends Thread{
    private int count = 5;

    @Override
    public synchronized  void run() {
        count --;
        System.out.println(this.currentThread().getName()+":"+count);
    }

    public static void main(String[] args) {
        MyThreadt myThreadt = new MyThreadt();
        Thread thread1 = new Thread(myThreadt, "A");
        Thread thread2 = new Thread(myThreadt, "B");
        Thread thread3 = new Thread(myThreadt, "C");
        Thread thread4 = new Thread(myThreadt, "D");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
