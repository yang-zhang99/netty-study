package org.yang.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try (
                //1、创建一个服务器Socket
                ServerSocket serverSocket = new ServerSocket(9876);

                //2、调用accept() 方法开始监听，等待客户端的请求
                Socket socket = serverSocket.accept();

        ) {

            System.out.println("***** 服务器启动，等待客户 *****");

            //3、获取输入流，读取客户端信息
            is = socket.getInputStream();

            //4、将字节输入流转换为字符输入流
            isr = new InputStreamReader(is);

            //5、为字符输入流添加缓存
            br = new BufferedReader(isr);
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说：" + info);
            }

            //6、关闭输入流
            socket.shutdownInput();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //7、关闭资源
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}