package work.biochat.server;

import lombok.extern.slf4j.Slf4j;
import work.biochat.entity.ChatMessage;
import work.biochat.utils.ConnectionUtil;
import work.biochat.utils.MessageUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BioServerHander implements Runnable {

    private Socket socket;

    private ConcurrentHashMap<String,String> uidMap ;

    public BioServerHander(Socket socket,ConcurrentHashMap<String, String> map) {
        this.socket = socket;
        this.uidMap = map;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            String result = null;
            ChatMessage message ;
            while (true){

                result = in.readLine();
                if (result == null) {
                    break;
                }

                message = MessageUtil.parseMessage(result);

                sendMessage(message);

                out.println(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            ConnectionUtil.releaseSocket(socket,in,out);
        }

    }

    private void sendMessage(ChatMessage message) {

        String flag = message.getFlag();
        String fromUid = message.getFromUid();
        List<String> toUid = message.getToUid();
        String content = message.getMsg();

        /*登陆*/
        if (flag.equals("0")){
            uidMap.put(message.getFromUid(),message.getFromUid());
        }else if (flag.equals("1")){
            if (uidMap.contains(message.getFromUid()))
            uidMap.remove(message.getFromUid());
        } else if(flag.equals("2")){
            for (String str : toUid) {
                ArrayList toUidList = new ArrayList();
                toUidList.add(str);
                ChatMessage cm = new ChatMessage(flag,fromUid,toUidList,content);
                doSendMessage(cm);
            }
        } else if(flag.equals("3")){
            doSendMessage(message);
        } else {
            log.warn("暂不支持的消息");
        }
    }

    private void doSendMessage(ChatMessage message) {

        String[] addr = message.getFromUid().split(":");
        String host = addr[0];
        String port = addr[1];

        String result = MessageUtil.deParseMessage(message);

        PrintWriter out = null;
        BufferedReader in = null;
        Socket socket = null;

        try {
            socket = new Socket(host, Integer.parseInt(port));
            out = new PrintWriter(socket.getOutputStream());
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
