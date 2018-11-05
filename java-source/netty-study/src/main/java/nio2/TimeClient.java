package nio2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @Author liujt
 * @Date 2018/11/5 16:52
 */
@Slf4j
public class TimeClient implements Runnable {

    private String name;

    private Random rnd = new Random();

    private InetSocketAddress remoteAddress;

    private Selector selector;

    private SocketChannel sc;

    private Charset charset = Charset.forName("UTF-8");

    public TimeClient(String name, InetSocketAddress inetSocketAddress) {

        this.name = name;
        this.remoteAddress = inetSocketAddress;
    }

    @Override
    public void run() {

        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*与服务器断开或线程中断则结束线程*/
        try {

            int i = 1;
            while (!Thread.currentThread().isInterrupted()){

                /*阻塞等待*/
                selector.select();

                /*Set中的每个key代表一个通道*/
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> it = keySet.iterator();

                /*遍历每个已就绪的通道，处理这个通道已就绪的事件*/
                while (it.hasNext()){
                     SelectionKey key = it.next();
                     /*防止下次select()返回已处理的channel*/
                    it.remove();

                    /*通过selectionKey获取通道对应的缓冲区*/
                    Buffers buffers = (Buffers) key.attachment();
                    ByteBuffer readBuffer = buffers.getReadBuffer();
                    ByteBuffer writeBuffer = buffers.getWriteBuffer();

                    /*通过selectionKey获取对应的通道*/
                    SocketChannel sc = (SocketChannel) key.channel();

                    /*底层socket缓冲区有可读数据时*/
                    if(key.isReadable()){

                    }

                    /*底层socket缓冲区可写*/
                    if (key.isWritable()){

                        /*从socket（channel）读缓冲区读取到自定义缓冲区中,返回读取的字符数*/
                        int len = 0;
                        sc.read(readBuffer);
                        StringBuffer sb = new StringBuffer();
                        while ((len = sc.read(readBuffer))>0){
                            readBuffer.flip();
                            /*utf-8解码*/
                            CharBuffer cb = charset.decode(readBuffer);
                            sb.append(cb.toString());

                            /*清空自定义缓存区*/
                            readBuffer.clear();
                        }

                        log.info("客户端消息：{}",sb.toString());

                    }

                    /*socket的写缓冲区可写*/
                    if (key.isWritable()){

                        writeBuffer.put((name + "  " + i).getBytes("UTF-8"));
                        writeBuffer.flip();
                        sc.write(writeBuffer);
                        writeBuffer.clear();
                        i++;
                    }

                }

                Thread.sleep(1000 + rnd.nextInt(1000));

            }

        } catch (InterruptedException e) {
            System.out.println(name + " is interrupted");
        } catch (IOException e) {
            System.out.println(name + " encounter a connect error");
        } finally {
            try {
                selector.close();
            } catch (IOException e1) {
                System.out.println(name + " close selector failed");
            } finally {
                System.out.println(name + "  closed");
            }
        }

    }

    private void doConnect() throws IOException {

        /*创建selector*/
        selector = Selector.open();
        /*创建TCP通道*/
        sc = SocketChannel.open();
        /*设置通道非阻塞*/
        sc.configureBlocking(false);

        /*注册感兴趣的事件*/
        int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;

        /*向selector注册通道*/
        sc.register(selector, interestSet, new Buffers(256, 256));

        /*向服务器发起一个连接，一个通道代表一个tcp连接*/
        sc.connect(remoteAddress);

        /*等待三次握手完成*/
        while (!sc.finishConnect()) {
            ;
        }

        log.info("{} finished connection.");

    }
}
