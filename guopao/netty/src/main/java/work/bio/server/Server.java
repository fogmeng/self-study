package work.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * BIO 服务端
 * @author SongQingWei
 * @date 2018年11月01 10:30
 */
public class Server {

    /** 默认端口号 */
    private static final Integer PORT = 8888;

    private static ServerSocket serverSocket;

    public static Map<String, Socket> socketMap = new HashMap<String, Socket>();

    public static void main(String[] args) {
        try {
            Server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void start() throws IOException {
        try {
            if (serverSocket != null) {
                return;
            }
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务端启动, 端口号:" + PORT);
            while (true) {
                System.out.println("等待客户端连接...");
                Socket socket = serverSocket.accept();
                new ServerHandler(socket).start();
            }
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
                System.out.println("服务端已关闭.");
            }
        }
    }
}
