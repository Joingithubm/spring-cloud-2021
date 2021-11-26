package com.athome.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: FileChannel 读取数据
 * @Author Zengfc
 * @Date 2021/10/27 10:05
 * @Version 1.0
 */
public class FileChannelRead {

    public static void main(String[] args) throws IOException {

        StringBuilder builder = new StringBuilder();

        // 1. 获取一个 channel
        RandomAccessFile aFile = new RandomAccessFile("D:\\SpringCloud\\日常充电\\NIO笔记\\demo\\01.txt","rw");
        FileChannel channel = aFile.getChannel();

        // 2. 创建 buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);

        // 3. 循环读取数据
        while (read != -1) {
            System.out.println(read);
            System.out.println("开始读取 buffer:");
            buffer.flip();
            while (buffer.hasRemaining()){
               builder.append((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }

        // 4. 关闭channel
        channel.close();

        System.out.println("读取结束："+builder.toString());

    }
}
