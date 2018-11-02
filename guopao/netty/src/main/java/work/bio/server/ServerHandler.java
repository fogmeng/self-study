package work.bio.server;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import work.bio.util.Constant;
import work.bio.util.ResponseMsg;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author SongQingWei
 * @date 2018年11月01 10:38
 */
public class ServerHandler extends Thread {

    private Socket socket;

    private InputStream inputStream = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader reader = null;
    private OutputStream outputStream = null;
    private PrintWriter writer = null;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            while (true) {
                ResponseMsg responseMsg = receivedMsg(reader);
                if (responseMsg == null) {
                    break;
                }
                System.out.println("发件人:" + responseMsg.getFrom() + ", 收到消息:" + responseMsg.getMsg() +
                        ", 发送时间:" + responseMsg.getSendTime() + ", 消息类型:" + responseMsg.getMsgType());
                sendMsg(responseMsg, writer);
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

    /**
     * 接收消息
     */
    private ResponseMsg receivedMsg(BufferedReader reader) throws IOException {
        String receiveMsg = reader.readLine();
        if (StringUtils.isBlank(receiveMsg)) {
            return null;
        }
        return new Gson().fromJson(receiveMsg, ResponseMsg.class);
    }

    /**
     * 发送消息
     */
    private void sendMsg(ResponseMsg responseMsg, PrintWriter writer) {
        PrintWriter printWriter = null;
        try {
            Integer msgType = responseMsg.getMsgType();
            if (msgType.equals(Constant.LOGIN)) {
                Server.socketMap.put(responseMsg.getFrom(), socket);
                ResponseMsg msg = new ResponseMsg("Server", Collections.singletonList(responseMsg.getFrom()), "欢迎进入咕泡聊天室", Constant.SERVER_PUSH_MANY, new Date());
                writer.println(new Gson().toJson(msg));
            } else if (msgType.equals(Constant.ONE_TO_ONE)) {
                if (Server.socketMap.containsKey(responseMsg.getTo().get(0))) {
                    Socket socket = Server.socketMap.get(responseMsg.getTo().get(0));
                    if (socket.isConnected()) {
                        printWriter = new PrintWriter(socket.getOutputStream(), true);
                        printWriter.println(new Gson().toJson(responseMsg));
                    }
                }
            } else if (msgType.equals(Constant.ONE_YO_MANY)) {
                for (String receivedName : responseMsg.getTo()) {
                    if (Server.socketMap.containsKey(receivedName)) {
                        Socket socket = Server.socketMap.get(receivedName);
                        if (socket.isConnected()) {
                            printWriter = new PrintWriter(socket.getOutputStream(), true);
                            printWriter.println(new Gson().toJson(responseMsg));
                        }
                    }
                }
            } else if (msgType.equals(Constant.SERVER_PUSH_MANY)) {
                for (Map.Entry<String, Socket> entry : Server.socketMap.entrySet()) {
                    Socket socket = entry.getValue();
                    if (socket.isConnected()) {
                        printWriter = new PrintWriter(socket.getOutputStream(), true);
                        printWriter.println(new Gson().toJson(responseMsg));
                    }
                }
            } else {
                System.out.println("类型错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
