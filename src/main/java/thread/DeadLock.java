package thread;

public class DeadLock {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A){
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized(B){
                        System.out.println("thread1=============");
                    }

                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B){
                    synchronized(A){
                        System.out.println("thread2=============");
                    }
                }
            }
        });
        thread2.start();
    }

}


