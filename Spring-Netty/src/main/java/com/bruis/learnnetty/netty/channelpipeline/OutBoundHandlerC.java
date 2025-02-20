package com.bruis.learnnetty.netty.channelpipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutBoundHandlerC extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("OutBoundHandlerC: " + msg);
        ctx.write(msg, promise);
//        throw new BusinessException("from OutBoundHandlerC");
    }
}
