package thread;

public class MyThread3 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });

        thread.start();
    }
}
