package bio.server;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author ll
 * @Date 2018/11/2 11:15
 */
@Slf4j
public class Server {

    /**
     * 默认端口号
     */
    private static int DEFAULT_PORT = 7777;

    /**
     * serverSocket
     */
    private static ServerSocket serverSocket ;


    public  static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int defaultPort) throws IOException {

        if (serverSocket != null) return;

        try {

            //构造ServerSocket
            serverSocket = new ServerSocket(defaultPort);

            //无限循环监听客户端连接
            //如果没有客户端接入，就阻塞到accept上

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHander(socket)).start();
            }

        }finally {

            if (serverSocket != null){

                serverSocket.close();
                log.info("服务已关闭");
                serverSocket = null;

            }


        }

    }

}
