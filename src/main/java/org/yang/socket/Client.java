package org.yang.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) {
        OutputStream os = null;
        PrintWriter pw = null;
        try (
                //1、创建客户端socket
                Socket socket = new Socket("localhost", 9876);

        ) {
            //2、 获取输出流，向服务端发送信息
            os = socket.getOutputStream();// 字节输台
            pw = new PrintWriter(os);//把输出流包装为打印流
            pw.write("!向服务器发送消息!");
            Thread.sleep(1000);
            pw.flush();
            socket.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //3、关闭资源
                if (pw != null) {
                    pw.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


