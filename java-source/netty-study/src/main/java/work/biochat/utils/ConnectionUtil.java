package work.biochat.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionUtil {

    public static void releaseServerSocket(ServerSocket serverSocket){

        if (serverSocket != null) {

            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            serverSocket = null;
        }
    }

    public static void releaseSocket(Socket socket, BufferedReader in, PrintWriter out) {

        if (in != null) {
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
