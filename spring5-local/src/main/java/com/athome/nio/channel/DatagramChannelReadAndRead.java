package com.athome.nio.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/27 15:12
 * @Version 1.0
 */
public class DatagramChannelReadAndRead {

    /**
     * 客户端发送消息
     */
    @Test
    public void clientChannel() throws IOException {

        DatagramChannel clientChannel = DatagramChannel.open();
        // clientChannel.socket().bind(new InetSocketAddress(10086));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(" I am  is client".getBytes());
        clientChannel.send(buffer, new InetSocketAddress("127.0.0.1", 10086));
        clientChannel.close();
    }

    /**
     * 服务端接收消息
     */
    @Test
    public void serverChannel() throws IOException {


        DatagramChannel clientChannel = DatagramChannel.open();
        clientChannel.socket().bind(new InetSocketAddress(10086));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketAddress receive = clientChannel.receive(buffer);
        System.out.println(receive.toString());

        System.out.println(buffer);

        clientChannel.close();
    }

}
