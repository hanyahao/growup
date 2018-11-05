package URL;

public class Cat implements Comparable<Cat> {
    private int size;
    private String brand;

    public Cat(int size, String brand) {
        this.size = size;
        this.brand = brand;
    }
    @Override
    public int compareTo(Cat cat) {
        if (this.size > cat.size) {
            return 1;
        }

        if (this.size < cat.size) {
            return -1;
        }

        return 0;
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat(12, "1");
        Cat cat2 = new Cat(12, "2");
        System.out.println(cat1.compareTo(cat2));

    }
}
