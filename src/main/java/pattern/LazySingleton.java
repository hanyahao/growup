package pattern;

public class LazySingleton {
    private static int STATUS = 1;
    private static LazySingleton lazySingleton;
    private LazySingleton() {
        System.out.println("create lazySingleton");
    }

    public  static LazySingleton getInstance() {
        if (null == lazySingleton) {
            synchronized (Singleton.class){
                if (null == lazySingleton) {
                    lazySingleton = new LazySingleton();
                }
            }

        }

        return lazySingleton;
    }

    public static void main(String[] args) {
//        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(LazySingleton.STATUS);

    }
}
