package work.bio.client;

import java.io.IOException;
import java.net.Socket;

/**
 * @author SongQingWei
 * @date 2018年11月01 11:06
 */
public class Client {

    private static final String HOST = "127.0.0.1";

    private static final Integer PORT = 8888;

    public static void main(String[] args) {
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        new ClientHandler(socket, "4").start();
    }
}
