package com.bruis.learnnetty.netty.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author lhy
 * @date 2021/8/19
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyEncoder extends MessageToByteEncoder<RemotingCommand> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RemotingCommand remotingCommand, ByteBuf out) {
        try {
            ByteBuffer header = remotingCommand.encodeHeader();
            out.writeBytes(header);
            byte[] body = remotingCommand.getBody();
            if (null != body) {
                out.writeBytes(body);
            }
//            out.writeBytes(remotingCommand.getBody());
        } catch (Exception e) {
            log.info("e: {}", e.getMessage());
            ctx.channel().close().addListener((ChannelFutureListener) future -> {
                // 关闭channel成功
            });
        }
    }
}
