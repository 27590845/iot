package com.xidian.iot.common.monitor.common;

import java.io.*;

/**
 * @description: 先将byteArrayOS绑定到数据源，然后把读到的字节流写到pipedOS里，消费者通过pipedIS获取
 * @author: mrl
 * @date: 2020/12/14 上午9:50
 */
public class ByteArrayIO {
    private PipedOutputStream pipedOS = new PipedOutputStream();
    private boolean keepRunning = true;
    private ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream() {
        public void close() {
            keepRunning = false;
            try {
                super.close();
                pipedOS.close();
            }
            catch(IOException e) {
                // 记录错误或其他处理
                // 为方便测试，此处直接结束
                System.exit(1);
            }
        }
    };
    private PipedInputStream pipedIS = new PipedInputStream() {
        public void close() {
            keepRunning = false;
            try    {
                super.close();
            }
            catch(IOException e) {
                // 记录错误或其他处理
                // 为方便测试，此处直接结束
                System.exit(1);
            }
        }
    };
    public ByteArrayIO() throws IOException {
        pipedOS.connect(pipedIS);
        startByteArrayReaderThread();
    }
    public InputStream getInputStream() {
        return pipedIS;
    }
    public OutputStream getOutputStream() {
        return byteArrayOS;
    }
    private void startByteArrayReaderThread() {
        new Thread(() -> {
            while(keepRunning) {
                // 检查流里面的字节数
                if(byteArrayOS.size() > 0) {
                    byte[] buffer = null;
                    synchronized(byteArrayOS) {
                        buffer = byteArrayOS.toByteArray();
                        byteArrayOS.reset(); // 清除缓冲区
                    }
                    try {
                        // 把提取到的数据发送给PipedOutputStream
                        pipedOS.write(buffer, 0, buffer.length);
                    } catch(IOException e) {
                        // 记录错误或其他处理
                        // 为简单计，此处我们直接结束
                        System.exit(1);
                    }
                } else {// 没有数据可用，线程进入睡眠状态
                    try {
                        // 每隔1秒查看ByteArrayOutputStream检查新数据
                        Thread.sleep(1000);
                    } catch (InterruptedException e) { }
                }
            }
        }).start();
    }
}
