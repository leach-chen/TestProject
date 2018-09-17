package com.leachchen.testjava;

/**
 * ClassName:   TestClassA.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 15:09
 **/
public class TestClassB extends TestClassA {

    public String name="child";

    static{
        System.out.println("test static TestClassB");
    }

    public TestClassB()
    {
        System.out.println("TestClassB run");
    }

    void run()
    {
        System.out.println("B run");
    }
}
