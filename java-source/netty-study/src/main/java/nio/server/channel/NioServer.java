package nio.server.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

@Slf4j
public class NioServer implements Runnable {

    /*服务器地址*/
    private InetSocketAddress localAddress;

    public NioServer(int port) {
        this.localAddress = new InetSocketAddress(port);
    }


    @Override
    public void run() {

        Charset utf8 = Charset.forName("UTF-8");

        ServerSocketChannel ssc = null;
        Selector selector = null;

        try {
            /*创建选择器*/
            selector = Selector.open();

            /*创建服务器通道*/
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);

            /*设置服务器端口，设置最大缓冲连接数*/
            ssc.bind(localAddress, 100);

            /*服务器通道只对tcp连接感兴趣*/
            ssc.register(selector, SelectionKey.OP_ACCEPT);


        } catch (IOException e) {
            e.printStackTrace();
            log.error("服务器启动失败。");
        }


    }
}
