package nettyPipeline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author liujt
 * @Date 2018/11/12 10:23
 */
public class PipeServer {

    private static final int PORT = 7777;
    private static final String IP = "127.0.0.1";

    private static final EventLoopGroup bossGroop = new NioEventLoopGroup(4);
    private static final EventLoopGroup workerGroop = new NioEventLoopGroup(8);

    public static void start() throws InterruptedException {

        try {

            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroop, workerGroop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline cp = socketChannel.pipeline();
                           // cp.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            //cp.addLast(new StringEncoder(CharsetUtil.UTF_8));

                            // 注册两个OutboundHandler，执行顺序为注册顺序的逆序，所以应该是OutboundHandler2 OutboundHandler1
                            cp.addLast(new OutboundHandler1());
                            cp.addLast(new OutboundHandler2());
                            // 注册两个InboundHandler，执行顺序为注册顺序，所以应该是InboundHandler1 InboundHandler2
                            cp.addLast(new InboundHandler1());
                            cp.addLast(new InboundHandler2());

                        }
                    }).option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = server.bind(PORT).sync();
            f.channel().closeFuture().sync();

        } finally {
            bossGroop.shutdownGracefully();
            workerGroop.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        PipeServer.start();
    }
}
