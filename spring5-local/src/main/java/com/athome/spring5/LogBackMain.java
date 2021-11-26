package com.athome.spring5;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/25 17:11
 * @Version 1.0
 */
public class LogBackMain {


    public static void main(String[] args) throws IOException, DocumentException, ParserConfigurationException {

        StringBuilder builder = new StringBuilder();
        System.out.println(Charset.availableCharsets());
        Charset charset = Charset.forName("UTF-8");

        // 1. 获取channel
        ClassPathResource resource = new ClassPathResource("json.txt");
        File file = resource.getFile();
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();

        // 2. 定义缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (channel.read(buffer) != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                CharBuffer decode = charset.decode(buffer);
                builder.append(decode.toString());
            }

            buffer.clear();
        }

        System.out.println(builder.toString().length());

        String resultStr = builder.toString();
        String replace = resultStr.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"");

        ClassPathResource resource1 = new ClassPathResource("json1.txt");
        File file1 = resource1.getFile();
        // 字符缓冲输出流：写东西到该文件
        BufferedWriter out = new BufferedWriter(new FileWriter(file1));
        // 写东西：\r\n即为换行
        out.write(replace);
        // 把缓存区内容压入文件

        Document document = DocumentHelper.parseText(replace);

        if(resultStr.contains("&lt;return&gt;") && resultStr.contains("&lt;/return&gt;")){
            resultStr = resultStr.substring(resultStr.indexOf("&lt;return&gt;")+14, resultStr.lastIndexOf("&lt;/return&gt;"));
/*                    .replace("&lt;","<")
                    .replace("&gt;",">")
                    .replace("&quot;","\"");*/
            //    log.info("替换转义标记后的结果：{}",resultStr);

        }else if(resultStr.contains("<return>") && resultStr.contains("</return>")){
            resultStr = resultStr.substring(resultStr.indexOf("<return>")+8, resultStr.lastIndexOf("</return>"));
/*                    .replace("&lt;","<")
                    .replace("&gt;",">")
                    .replace("&quot;","\"");*/
            //    log.info("替换转义标记后的结果：{}",resultStr);

        }else{

        }




    }

    static class BizHandle extends Thread {

        private  final Logger logger = LoggerFactory.getLogger(LogBackMain.class);
        public static final String REQ_ID = "task-name";
        public static final String THEAD_ID = "thread-name";
        private String funCode;

        public BizHandle(String funCode) {
            super(funCode);
            this.funCode = funCode;
        }

        @Override
        public void run() {
            MDC.put(REQ_ID, REQ_ID);
       //     MDC.put(THEAD_ID, funCode);
            logger.info("开始调用服务{}，进行业务处理", funCode);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
            logger.info("服务{}处理完毕，可以释放空间了，避免内存泄露", funCode);
        //    MDC.remove(REQ_ID);
        }
    }

    static class BizHandle1 extends Thread {

        private  final Logger logger = LoggerFactory.getLogger(LogBackMain.class);
        public static final String REQ_ID = "task-name";
        public static final String THEAD_ID = "thread-name";

        private String funCode;

        public BizHandle1(String funCode) {
            super(funCode);
            this.funCode = funCode;
        }

        @Override
        public void run() {
            MDC.put(REQ_ID, REQ_ID);
        //    MDC.put(THEAD_ID, funCode);
            logger.info("开始调用服务{}，进行业务处理", funCode);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
            logger.info("服务{}处理完毕，可以释放空间了，避免内存泄露", funCode);
         //   MDC.remove(REQ_ID);
        }
    }

}
