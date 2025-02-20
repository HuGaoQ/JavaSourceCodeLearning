package com.bruis.learnnetty.netty.quickstart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LuoHaiYang
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    public NettyClientHandler() {
        super();
        System.out.println("NettyClientHandler构造方法");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("channelRegistered...注册通道");
    }

    /**
     * 什么时候调用？
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        System.out.println("channelUnregistered...解除注册通道");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("channelInactive...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("channelReadComplete...读取通道完成之后的回调方法");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        System.out.println("userEventTriggered...用户事件触发器");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) {
        System.out.println("channelWritabilityChanged...");
    }

    /**
     * 当通道就绪就会触发该方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("client " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server", CharsetUtil.UTF_8));
    }

    /**
     * 当通道有读取事件时，就会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端回复的消息：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("cause: {}", cause.getMessage());
        ctx.close();
    }
}
