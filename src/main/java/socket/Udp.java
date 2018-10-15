package socket;

import java.io.IOException;
import java.net.*;

public class Udp {
}


class UdpServer{
    public static void main(String[] args) throws IOException {
        //1.创建服务器端DategramSocket,指定端口
        DatagramSocket dategramSocket = new DatagramSocket(8800);
        //2.创建数据包，用于接收客户端发送的数据
        byte[] data = new byte[1024];
        //3.接收客户端发送的数据
        System.out.println("服务器已经开启，等待客户端的链接");
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        //此方法在接收到数据之前会一直阻塞
        dategramSocket.receive(datagramPacket);
        //4.读取客户端发送的数据
        //参数：data要转换从数组的下标0开始  datagramPacket.getLength() 长度为接收到的长度
        String info = new String(data,0,datagramPacket.getLength());
        System.out.println("这里是服务器，客户端发来的消息：--"+info);

        /**
         *  向客户端进行响应
         */
        //1、定义客户端的地址、端口号、数据
        //获取客户端 ip地址
        InetAddress inetAddress = datagramPacket.getAddress();
        //获取客户端端口号
        int port = datagramPacket.getPort();
        //将要响应的内容保存到byte数组中
        byte [] data2 = "欢迎您！".getBytes();
        //2创建数据报，包含响应的数据信息
        DatagramPacket datagramPacket12 = new DatagramPacket(data2,data2.length,inetAddress,port);
        //3、响应客户端
        dategramSocket.send(datagramPacket12);
        //4、关闭资源
        dategramSocket.close();
    }
}


class UdpClient {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {

        //1、定义服务器地址、端口号、数据

        InetAddress inetAddress = InetAddress.getByName("localhost");
        int port = 8800;

        byte[] data = "用户名： 最帅的；密码： 123".getBytes();

        //2、创建数据报，包含发送的信息

        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);

        //3、创建DatagramSocket对象

        DatagramSocket datagramSocket = new DatagramSocket();

        //4、向服务器端发送数据报

        datagramSocket.send(datagramPacket);


        /**

         * 客户端接收服务器端响应信息

         *

         */

        //1、创建数据报，用于接收服务器端响应数据，数据保存到字节数组中

        byte[] data2 = new byte[1024];

        DatagramPacket datagramPacket1 = new DatagramPacket(data2, data2.length);

        //2、接收服务器响应的数据

        datagramSocket.receive(datagramPacket1);

        //3、读取数据

        String reply = new String(data2, 0, datagramPacket1.getLength());

        System.out.println("这里是客户端，服务器端发来的消息：--" + reply);

        //4、关闭资源

        datagramSocket.close();
    }
}




