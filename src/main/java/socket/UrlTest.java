package socket;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UrlTest {
    //    public static void main(String[] args) throws IOException {
//        URL url = new URL("btbo://87e56500b9a5065aa3fcef8da7f70fd8/巨齿鲨HD大陆公映国英双语中字[飘花www.piaohua.com].mkv");
//        System.out.println(url.getPort());
//        System.out.println(url.getProtocol());
//        System.out.println(url.getAuthority());
//        InputStream inputStream =  url.openStream();
//        OutputStream outputStream = new FileOutputStream("logo.jpg");
//        byte[] bytes = new byte[1024];
//        int hasRead = 0;
//        while ((hasRead = inputStream.read(bytes)) > 0) {
//            System.out.println("---");
//            outputStream.write(bytes, 0, hasRead);
//
//        }
////        InputStreamReader reader = new InputStreamReader(inputStream);
////        BufferedReader bufferedReader = new BufferedReader(reader);
////        System.out.println(bufferedReader);
////        String data = null;
////        while ( (data = bufferedReader.readLine()) != null) {
////            System.out.println(data);
////        }
//        inputStream.close();
//        outputStream.close();
//    }
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://wwww.baidu.com");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        Map<String, List<String>> map = httpURLConnection.getHeaderFields();
//        for (Map.Entry<String, List<String>> data : map.entrySet()) {
//            List<String> list = new ArrayList<>();
//            for (String head : list) {
//                System.out.println(head);
//            }
//        }
        System.out.println(httpURLConnection.getResponseCode()+"  "+httpURLConnection.getResponseMessage());
        System.out.println(httpURLConnection.getRequestMethod());
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String data = "";
        while (( data = bufferedReader.readLine()) != null)
            System.out.println(data);
    }
}
