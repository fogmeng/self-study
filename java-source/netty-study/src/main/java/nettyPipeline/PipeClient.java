package nettyPipeline;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author liujt
 * @Date 2018/11/12 10:47
 */
public class PipeClient {

    private static final String IP = "127.0.0.1";
    private static  final int PORT = 7777;

    private static  final EventLoopGroup workGroup = new NioEventLoopGroup();

    public static void connet() throws InterruptedException {

        try {

            Bootstrap b = new Bootstrap();
            b.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline cp = socketChannel.pipeline();
                            //cp.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            //cp.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            cp.addLast(new PipeClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(IP,PORT).sync();
            f.channel().closeFuture().sync();

        } finally {
            workGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        PipeClient.connet();
    }

}
