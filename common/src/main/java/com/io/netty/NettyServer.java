package com.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-01-06 20:33
 */
public class NettyServer {
    public static void main(String[] args) throws FileNotFoundException {
        NettyServer server = new NettyServer();
        server.bind(20000);
    }

    public void bind(int port) throws FileNotFoundException {
        String time = new Date().getTime()+"";
        String path = "D:\\idea_study2\\common-util\\common\\src\\main\\java\\com\\io\\netty\\b"+time+".jpg";


        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .handler(new LoggingHandler())
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
// 写文件
                                File f = new File(path);
                                final FileOutputStream fos = new FileOutputStream(f, true);
                                ByteBuf byteBuf = (ByteBuf) msg;
                                byte[] bs = new byte[byteBuf.readableBytes()];
                                byteBuf.readBytes(bs);
                                System.out.println("服务端本次接收长度：" + bs.length);

                                fos.write(bs);
                                fos.flush();
                                fos.close();
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                super.channelReadComplete(ctx);
                                System.out.println("读取complete");
                            }
                        });
                    }
                });

        ChannelFuture future = null;
        try {
            future = server.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
