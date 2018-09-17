package com.leachchen.testjava;

import java.io.File;

/**
 * ClassName:   testPart.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 15:03
 **/
public class TestPart {

    /**
     * try catch finally执行
     */
    public void testPart1()
    {
        int a = 0;
        try
        {
            a = 10 / 0;
            System.out.println("aa:"+a);
            return;
        }catch (Exception e)
        {
            a = 20;
            System.out.println("bb:"+a);
            return;
        }finally {
            a = 30;
            System.out.println("cc:"+a);
            return;
        }
    }

    public void testPart2()
    {
       String str = "C://myFile/a/b/c/d/a.text";
       System.out.println(str.substring(str.indexOf("d")+1+1,str.length()));
    }

}
