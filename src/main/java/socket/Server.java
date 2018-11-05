package socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = null;
        System.out.println("***服务器即将启动，等待客户端连接*****");
        while (true) {
            socket = serverSocket.accept();
            InetAddress address = socket.getInetAddress();
            System.out.println("客户端ip地址："+address.getHostAddress());

        }
    }
}
