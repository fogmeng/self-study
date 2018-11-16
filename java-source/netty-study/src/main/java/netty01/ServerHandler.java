package netty01;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liujt
 * @Date 2018/11/9 15:32
 */
@Slf4j
public class ServerHandler extends  ChannelInboundHandlerAdapter{

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("server receive msg:{}",msg);
        ctx.write(msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause == null) {
            log.info("Exception:",cause.getMessage());
        }
    }

}
