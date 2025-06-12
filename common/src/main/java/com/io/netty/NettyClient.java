package com.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.FileInputStream;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-01-06 20:47
 */
public class NettyClient {
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.bind("127.0.0.1", 20000);
    }

    public void bind(String ip, int port) {
        EventLoopGroup work = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                String file = "D:\\idea_study2\\common-util\\common\\src\\main\\java\\com\\io\\netty\\a.jpg";
                                FileInputStream fis = new FileInputStream(file);
                                byte[] bs = new byte[10240];
                                int n = 0;
                                while ((n = fis.read(bs)) != -1) {
                                    ByteBuf buffer = Unpooled.buffer(bs.length);
                                    buffer.writeBytes(bs, 0, n);
                                    ctx.writeAndFlush(buffer);
                                    buffer.clear();
                                }
                            }
                        });
                    }
                });

        ChannelFuture f = null;
        try {
            f = bootstrap.connect(ip, port).sync();
//            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
        }

    }
}
