package thread.productconsumer;

public class PCData {
    private int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
