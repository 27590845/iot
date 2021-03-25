package com.xidian;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
    public void show(){
        String sss = "http://125.46.16.2:10080/hls/jinluzt005/jinluzt005_live.m3u8";
        String ssss = sss.split("/")[4];
        System.out.println(ssss);
    }
}
