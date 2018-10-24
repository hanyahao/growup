package pattern.factory;

public class Factory {
    public static Car getInstance(String car) {
        switch (car) {
            case "AoDi":
                return new AoDiCar();
            case "BenChi" :
                return new BenChi();
                default:
                    return null;
        }

    }

    public static void main(String[] args) {
        Car aodi = Factory.getInstance("AoDi");
        aodi.car();
        Car benchi = Factory.getInstance("BenChi");
//        benchi.
    }
}
