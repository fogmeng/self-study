package nio2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @Author liujt
 * @Date 2018/11/5 10:40
 */
@Slf4j
public class TimeServer implements Runnable {

    /*默认端口号*/
    private static int DEFAULT_PORT = 7777;

    private InetSocketAddress inetSocketAddress;

    private Selector selector;

    private ServerSocketChannel ssc;

    private Charset utf8;

    private volatile boolean stop;

    public TimeServer() {

        utf8 = Charset.forName("UTF-8");
        inetSocketAddress = new InetSocketAddress(DEFAULT_PORT);
        stop = false;
        try {
            selector = Selector.open();
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(inetSocketAddress, 100);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("server start failed.");
        }
    }

    public void stop() {
        this.stop = true;
    }


    @Override
    public void run() {

        while (!stop) {

            try {
                int n = selector.select();
                if (n == 0) {
                    continue;
                }

                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> it = keySet.iterator();
                SelectionKey key = null;

                while (it.hasNext()) {
                    key = it.next();
                    /*防止下次select方法返回已处理过的通道*/
                    it.remove();

                    /*若发现异常，说明客户端连接出现问题,但服务器要保持正常*/
                    handleSelectionKey(key);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    selector.close();
                } catch (IOException e) {
                    System.out.println("selector close failed");
                } finally {
                    System.out.println("server close");
                }

            }

        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleSelectionKey(SelectionKey key) throws IOException, InterruptedException {

        Random rnd = new Random();

        try {

            /*scc通道对连接事件感兴趣*/
            if (key.isAcceptable()) {

                /*accept获取一个普通通道，每个通道对应内核中的socket缓冲区*/
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);

                /*向选择器注册这个通道和普通通道感兴趣的事件，同时提供这个新通道相关的缓冲区*/
                sc.register(selector, SelectionKey.OP_READ, new Buffers(256, 256));
            }

            /*通道感兴趣读事件，并且可读*/
            if (key.isReadable()) {

                /*通过SelectionKey获取通道对应的缓冲区*/
                Buffers buffers = (Buffers) key.attachment();
                ByteBuffer readBuffer = buffers.getReadBuffer();
                ByteBuffer writeBuffer = buffers.getWriteBuffer();

                /*通过selectionKey获取对应通道*/
                SocketChannel sc = (SocketChannel) key.channel();

                /*从底层socket读取缓冲区中读入数据*/
                sc.read(readBuffer);
                readBuffer.flip();

                /*解码显示，客户端发送过来的东西*/
                CharBuffer cb = utf8.decode(readBuffer);
                log.info("readBuffer:{}", cb.array());

                readBuffer.rewind();

                /*准备好向客户端发送信息*/
                /*先写入信息，再将发送过来的信息传入*/
                writeBuffer.put("echo from service:".getBytes("UTF-8"));
                writeBuffer.put(readBuffer);

                key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
            }

            /*通道感兴趣写事件*/
            if (key.isWritable()) {

                Buffers buffers = (Buffers) key.attachment();
                ByteBuffer writeBuffer = buffers.getWriteBuffer();
                writeBuffer.flip();

                SocketChannel sc = (SocketChannel) key.channel();

                int len = 0;
                while (writeBuffer.hasRemaining()) {
                    /*返回0时，说明底层的socket写缓冲已满*/
                    len = sc.write(writeBuffer);
                    /*说明底层的socket写缓冲已满*/
                    if (len == 0) {
                        break;
                    }
                }

                writeBuffer.compact();

                /*说明数据全部写入到底层的socket写缓冲区*/
                if (len != 0) {
                    /*取消通道的写事件*/
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                }
            }

            Thread.sleep(rnd.nextInt(500));

        } catch (IOException e) {
            e.printStackTrace();
            log.error("service encounter clinet error ");
            /*若客户端连接出现异常，从Seletcor中移除这个key*/
            key.cancel();
            key.channel().close();

        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeServer server = new TimeServer();
        new Thread(server).start();

        Thread.sleep(1000*60);
        server.stop();
    }

}
