package bio;

import bio.client.BioClient;
import bio.server.Server;
import com.sun.deploy.util.SessionState;

import java.io.IOException;

/**
 * @Author liujt
 * @Date 2018/11/2 17:50
 */
public class AppTest {


    public static void main(String[] args) throws InterruptedException{

        new Thread(()-> {
            try {
                Server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(()->{

            BioClient.send("a");

        }).start();



    }




}
