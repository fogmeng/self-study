package bio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * @Author ll
 * @Date 2018/11/2 11:17
 */
@Slf4j
public class ServerHander implements Runnable {

    private  Socket socket = null;

    public ServerHander(Socket socket) {

        this.socket = socket;

    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            String result = null;

            while (true){

                //通过bufferedReader读取一行
                //如果读取到流的尾部，返回null,跳出循环
                //如果得到非空值，处理逻辑并返回
                result =  in.readLine();
                if (result == null) break;
                log.info("服务端接收报文："+result);

                out.println(result);

            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null){
                out.close();
                out = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }

        }

    }
}
