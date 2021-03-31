package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void strCoding() throws UnsupportedEncodingException, InterruptedException {

        Timer timer = new Timer(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task1---");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0);
        Thread.sleep(1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task2---");
            }
        }, 0);

//        String str = "你好 hello";
//        byte[] bytes = str.getBytes("ISO8859-1");
//        String str2 = new String(bytes, "ISO8859-1");
//        byte[] bytes2 = str2.getBytes("utf8");
//        String str3 = new String(bytes2, "utf8");
//        System.out.println(str);
//        System.out.println(str2);
//        System.out.println(str3);
        Thread.sleep(10000);
    }
}
