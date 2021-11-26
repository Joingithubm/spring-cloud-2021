package com.athome.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/27 14:44
 * @Version 1.0
 */
public class SocketChannelOpen {
    public static void main(String[] args) throws IOException {

        StringBuilder builder = new StringBuilder();

        // 1. 创建 socketChannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("www.baidu.com",80));

        // 2. 设置缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);

        // 3. 打印返回结果
        while (read != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                builder.append((char) buffer.get());
            }
            buffer.clear();
        }

        channel.close();

        System.out.println("执行结束");
    }
}
