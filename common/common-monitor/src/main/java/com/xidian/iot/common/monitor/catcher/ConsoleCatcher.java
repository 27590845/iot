package com.xidian.iot.common.monitor.catcher;

import com.xidian.iot.common.monitor.Frame;
import com.xidian.iot.common.monitor.Parser;
import com.xidian.iot.common.monitor.common.ByteArrayIO;
import com.xidian.iot.common.monitor.FrameCaughtListener;
import lombok.Setter;

import javax.swing.*;
import java.io.*;

/**
 * @description: 获取当前进程的控制台输出
 * @author: mrl
 * @date: 2020/12/14 上午10:13
 */
public class ConsoleCatcher {

    /**
     * 注意，不能在在listener中做同级别的控制台输出动作，否则会出现"捕获后输出，输出后又被捕获"的现象
     * 比如捕获warn级别的输出，然后又在listener中输出了warn级别的输出，这时又会被捕获
     */
    @Setter
    private FrameCaughtListener listener;
    @Setter
    private Parser parser;
    //捕获什么类型的输出，默认捕获所有
    @Setter
    private int catchType = Frame.TYPE_ALL;
    //用来读控制台输出
    private InputStream inStream;

    public ConsoleCatcher() throws IOException {
        final ByteArrayIO ls = new ByteArrayIO();
        // 重定向System.out和System.err
        PrintStream ps = new PrintStream(ls.getOutputStream());
        System.setOut(ps);
        System.setErr(ps);
        this.inStream = ls.getInputStream();
    }
    private void startConsoleReaderThread() {
        final String frameHeaderTag = parser.getFrameHeaderTag();
        final BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
        new Thread(() -> {
            StringBuffer sb = new StringBuffer();
            try {
                String s;
                while((s = br.readLine()) != null) {
                    if(s.startsWith(frameHeaderTag) && sb.length()>0){
                        Frame frame = parser.parseFrame(sb.toString());
                        if(frame.getType()==catchType || Frame.TYPE_ALL==catchType) {
                            listener.onFrameCaught(frame);
                        }
                        sb.setLength(0);
                    }
                    sb.append(s).append('\n');
                }
            }
            catch(IOException e) {
                JOptionPane.showMessageDialog(null, "从BufferedReader读取错误：" + e);
                System.exit(1);
            }
        }).start();
    }
}
