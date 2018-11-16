package netty01;

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
 * @Date 2018/11/9 16:05
 */
public class NettyClient implements Runnable {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 7777;

    @Override
    public void run() {

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline cp = socketChannel.pipeline();
                            cp.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                            cp.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                            cp.addLast(new ClientHandler());
                        }
                    });


            ChannelFuture cf = bootstrap.connect(IP, PORT).sync();
            cf.channel().writeAndFlush(Thread.currentThread().getName()+" send msg: hello server!");
            //cf.channel().closeFuture().sync();
            cf.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        //for (int i =0;i< 10;i++){
            new Thread(new NettyClient(),"Thread-->"+1).start();
        //}
    }
}
