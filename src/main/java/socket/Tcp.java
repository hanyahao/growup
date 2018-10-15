package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Tcp {
}

class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4800);
        System.out.println("server start is ok...");
        //创建一个ServerSocket在端口4800监听客户请求
        //使用accept()阻塞等待客户请求，有客户
        Socket socket = server.accept();
        //请求到来则产生一个Socket对象，并继续执行
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //由Socket对象得到输入流，并构造相应的BufferedReader对象
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        //由Socket对象得到输出流，并构造PrintWriter对象
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //由系统标准输入设备构造BufferedReader对象
        System.out.println("Client:" + in.readLine());
       //在标准输出上打印从客户端读入的字符串
        line = br.readLine();
        //从标准输入读入一字符串
        while (!line.equals("end")) {
            //如果该字符串为 "end"，则停止循环
            writer.println(line);
            //向客户端输出该字符串
            writer.flush();
            //刷新输出流，使Client马上收到该字符串
            System.out.println("Server:" + line);
            //在系统标准输出上打印读入的字符串
            System.out.println("Client:" + in.readLine());
            //从Client读入一字符串，并打印到标准输出上
            line = br.readLine();
            //从系统标准输入读入一字符串
        } //继续循环
        writer.close(); //关闭Socket输出流
        in.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
        server.close(); //关闭ServerSocket
    }

}

class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 4800);
        System.out.println("client start ...");
        //向本机的4800端口发出客户请求
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //由系统标准输入设备构造BufferedReader对象
        PrintWriter write = new PrintWriter(socket.getOutputStream());
        //由Socket对象得到输出流，并构造PrintWriter对象
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //由Socket对象得到输入流，并构造相应的BufferedReader对象
        String readline;
        readline = br.readLine(); //从系统标准输入读入一字符串
        while (!readline.equals("end")) {
            //若从标准输入读入的字符串为 "end"则停止循环
            write.println(readline);
            //将从系统标准输入读入的字符串输出到Server端
            write.flush();
            //刷新输出流，使Server马上收到该字符串
            System.out.println("Client:" + readline);
            //在系统标准输出上打印读入的字符串
            System.out.println("Server:" + in.readLine());
            //从Server读入一字符串，并打印到标准输出上
            readline = br.readLine(); //从系统标准输入读入一字符串
        } //继续循环
        write.close(); //关闭Socket输出流
        in.close(); //关闭Socket输入流
        socket.close(); //关闭Socket
    }
}
