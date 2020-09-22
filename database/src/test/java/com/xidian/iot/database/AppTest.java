package com.xidian.iot.database;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

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
    public void numLenValid(){
        int[] lens = new int[]{18, 19};
        Long timeStamp = System.currentTimeMillis()>>16;
        Long num = timeStamp.longValue()<<32;
        boolean flag;
        boolean binary = false;
        //因为最后一次右移后，count不会自增，所以count初始值为1
        int count = 1;
        if(binary){
            while ((num >>= 1) > 0) ++count;
        }else {
            while ((num /= 10) > 0) ++count;
        }
        int finalCount = count;
        flag = Arrays.stream(lens).filter(x -> x==finalCount).count() > 0;
        System.out.println(flag);
    }

    @Test
    public void patternValid(){
        String regexp1 = "^(-?\\d+)(\\.\\d+)?$";
        String str1 = "23.18";
        System.out.println(Pattern.matches(regexp1, str1));
        String regexp2 = "[0-9a-zA-Z]{1,6}";
        String str2 = "23";
        System.out.println(Pattern.matches(regexp2, str2));
    }
}
