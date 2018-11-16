package netty01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liujt
 * @Date 2018/11/9 14:33
 */
@Slf4j
public class NettyServer {

    /**
     * ip
     */
    private static final String IP = "127.0.0.1";

    /**
     * port
     */
    private static final int PORT = 7777;

    /**
     * boss组
     */
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(4);

    /**
     * worker组
     */
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(10);

    public static void start() throws InterruptedException {

        //初始化server
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler())
            .childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    ChannelPipeline cp = socketChannel.pipeline();
                    cp.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    cp.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    cp.addLast(new ServerHandler());
                }
            });

        ChannelFuture cf = server.bind(IP,PORT).sync();
        cf.channel().closeFuture().sync();
        log.info("server started. ip:{}:{}",IP,PORT);

    }

    public static void shutdown(){

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("server starting ");
        NettyServer.start();
    }

}
