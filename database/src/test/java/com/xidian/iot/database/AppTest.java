package com.xidian.iot.database;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

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

    private static String getSceneSnFromTopicName(String topicName){
        String res = null;
        if(StringUtils.isNotEmpty(topicName)){
            res = topicName.substring(topicName.lastIndexOf('.'));
        }
        return res;
    }

    @Test
    public void test1(){
        System.out.println(getSceneSnFromTopicName("123.456"));
    }
}
