package work.bio.client;

import com.google.gson.Gson;
import work.bio.util.Constant;
import work.bio.util.ResponseMsg;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author SongQingWei
 * @date 2018年11月01 11:15
 */
public class ClientHandler extends Thread {

    private Socket socket;

    private String name;

    public ClientHandler(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        OutputStream outputStream = null;
        PrintWriter writer = null;
        try {
            outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            ResponseMsg responseMsg = new ResponseMsg(name, null, null, Constant.LOGIN, new Date());
            writer.println(new Gson().toJson(responseMsg));
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String receiveMsg;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                receiveMsg = reader.readLine();
                System.out.println("收到消息:" + receiveMsg);
                sendMsg(writer, bufferedReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMsg(PrintWriter writer, BufferedReader bufferedReader) throws IOException {
        System.out.print("输入消息类型：");
        Integer msgType = Integer.parseInt(bufferedReader.readLine());
        System.out.print("输入接受人：");
        String to = bufferedReader.readLine();
        List<String> tos;
        if (to.contains(",")) {
            String[] split = to.split(",");
            tos = Arrays.asList(split);
        } else {
            tos = Collections.singletonList(to);
        }
        System.out.print("输入消息：");
        String message = bufferedReader.readLine();
        ResponseMsg msg = new ResponseMsg(name, tos, message, msgType, new Date());
        writer.println(new Gson().toJson(msg));
    }
}
