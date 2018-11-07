package work.biochat.server;

import lombok.extern.slf4j.Slf4j;
import work.biochat.utils.ConnectionUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class BioServer {

    /**
     * 默认端口号
     */
    private static int DEFAULT_PORT = 7777;

    private static ServerSocket serverSocket;

    public static void start() {
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int defaultPort) {

        if (serverSocket != null) {
            return;
        }

        try {
            serverSocket = new ServerSocket(defaultPort);

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(new BioServerHander(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.releaseServerSocket(serverSocket);
        }

    }


}
