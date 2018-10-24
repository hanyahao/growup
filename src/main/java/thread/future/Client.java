package thread.future;

public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(){
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();

        return futureData;
    }


    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");

        try {

            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        System.out.println("data = "+data.getResult());
    }
}
