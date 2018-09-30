package com.leachchen.testjava;

/**
 * ClassName:   TestClassA.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 15:09
 **/
public class TestClassParent{

    public String name = "parent";

    public String aa="child";


    static{
        System.out.println("father static code part");
    }

    {
        System.out.println("father code part");
    }

    public TestClassParent()
    {
        System.out.println("father consult");
    }

    public void run()
    {
        System.out.println("TestClassParent run:"+name);
        final int b = 1;
        class AA
        {
            public void tt()
            {
                System.out.println("aa:"+b);
            }
        }
    }

    class BB
    {
        public BB()
        {
            System.out.println("name:"+name);
        }
    }
}

