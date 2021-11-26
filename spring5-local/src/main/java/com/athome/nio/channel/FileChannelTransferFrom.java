package com.athome.nio.channel;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Description: 从一个通道传输到另一个通道
 * @Author Zengfc
 * @Date 2021/10/27 14:07
 * @Version 1.0
 */
public class FileChannelTransferFrom {

    public static void main(String[] args) throws Exception {
        // 1. 获取一个 channel
        RandomAccessFile aFile = new RandomAccessFile("D:\\SpringCloud\\日常充电\\NIO笔记\\demo\\01.txt","rw");
        FileChannel channel = aFile.getChannel();

        // 1. 获取一个 channel
        RandomAccessFile bFile = new RandomAccessFile("D:\\SpringCloud\\日常充电\\NIO笔记\\demo\\02.txt","rw");
        FileChannel bChannel = bFile.getChannel();

        long position = 0;
        long size = channel.size();
        System.out.println(size);
        bChannel.transferFrom(channel,position,size);

        channel.close();
        bChannel.close();

        System.out.println("传输完毕");
    }


}
