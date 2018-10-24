package thread.future;

public class RealData implements Data{
    public final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return null;
    }
}
