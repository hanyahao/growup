package URL;

public class Dog {
    String strA = "www.baidu.com";

    public Dog() {
        System.out.println("构造器初始化");
    }


    static{
        System.out.println("静态初始化");
    }

    {
        System.out.println("对象初始化");
    }

    {
        System.out.println(strA);
    }

    public static void main(String[] args) {
        new Dog();
        new Dog();
    }
}
