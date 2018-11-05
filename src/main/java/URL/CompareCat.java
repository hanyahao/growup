package URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareCat {
    public int size;
    private String brand;

    public CompareCat(int size, String brand) {
        this.size = size;
        this.brand = brand;
    }


    public static void main(String[] args) {
        CompareCat c1 = new CompareCat(23, "1");
        CompareCat c2 = new CompareCat(12, "2");
        CompareCat c3 = new CompareCat(19, "2");
        List<CompareCat> compareCatList = new ArrayList<>();
        compareCatList.add(c1);
        compareCatList.add(c2);
        compareCatList.add(c3);

        Collections.sort(compareCatList, new SizeComparator());
        for (CompareCat cat : compareCatList) {
            System.out.println(cat.size);
        }
    }

}

class SizeComparator implements Comparator<CompareCat>{
    @Override
    public int compare(CompareCat o1, CompareCat o2) {
        if (o1.size > o2.size) {
            return 1;
        }

        if (o1.size < o2.size) {
            return -1;
        }

        return 0;
    }
}

