package com.xidian.iot;

import static org.junit.Assert.assertTrue;

import com.xidian.iot.common.util.TimeUtil;
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
    public void testTimeUtil(){
        System.out.println(TimeUtil.getTimeStamp(null));
    }
}
