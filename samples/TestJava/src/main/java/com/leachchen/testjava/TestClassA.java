package com.leachchen.testjava;

/**
 * ClassName:   TestClassA.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 15:09
 **/
public class TestClassA {

    public String name="parent";

    static{
        System.out.println("test static TestClassA");
    }

    public TestClassA()
    {
        System.out.println("TestClassA run");
    }

    void run()
    {
        System.out.println("A run");
    }
}
