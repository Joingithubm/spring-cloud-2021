package com.athome.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/27 10:29
 * @Version 1.0
 */
public class FileChannelWrite {
    public static void main(String[] args) throws IOException {

        String str = "I LOVE YOU YOU LOVE ME WE ARE SWEET !";
        final int BUFFER_SIZE = 1024;

        // 1. 创建 channel
        RandomAccessFile file = new RandomAccessFile("D:\\SpringCloud\\日常充电\\NIO笔记\\demo\\02.txt","rw");
        FileChannel channel = file.getChannel();

        // 2. 将数据读取到 buffer 中
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.clear();
        buffer.put(str.getBytes(StandardCharsets.UTF_8));
        buffer.flip();

        // 3. 循环写入文件

        while (buffer.hasRemaining()){
            channel.write(buffer);
        }

        // 4. 关闭 channel
        channel.close();

        System.out.println("写入数据结束！");
    }
}
