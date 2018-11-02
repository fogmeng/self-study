package bio.client;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author liujt
 * @Date 2018/11/2 11:17
 */
@Slf4j
public class BioClient {

    /**
     * 服务端默认端口
     */
    private static int DEFAULT_SERVER_PORT = 7777;

    /**
     * ip地址
     */
    private static String HOST = "127.0.0.1";


    public static void send(String msg){

        send(DEFAULT_SERVER_PORT,msg);
    }

    private static void send(int defaultServerPort, String msg) {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String result = null;

        try {
            socket = new Socket(HOST,defaultServerPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            out.println(msg);

            result = in.readLine();

            log.info("结果为："+result);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null) {
                out.close();
                out = null;
            }

            if (socket != null){
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
