package com.example.demo.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 客户端消息处理
 * @author -琴兽-
 *
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("客户端收到消息:"+msg);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
