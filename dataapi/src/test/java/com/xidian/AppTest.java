package com.xidian;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

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
    public void mathParserTest(){
        Function f = new Function("f(x,y)=sin(x/10)+y");
        double res = f.calculate(new Argument("x=30"), new Argument("y=1"));
        System.out.println(res);

        Expression expression = new Expression("sin(x/10)");
        expression.addArguments(new Argument("x=30"));
        res = expression.calculate();
        System.out.println(res);
        expression.removeAllArguments();
        expression.addArguments(new Argument("x=12"));
        res = expression.calculate();
        System.out.println(res);
    }
}
