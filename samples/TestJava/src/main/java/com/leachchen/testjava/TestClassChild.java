package com.leachchen.testjava;

/**
 * ClassName:   TestClassA.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 15:09
 **/
public class TestClassChild extends TestClassParent  implements TestInterface {

    public String name="child";

    public static TestClassChild mInstance = new TestClassChild();  //这句代码优先于下面的static代码体执行(若static代码段放在本行之上，则优先执行)，new了之后先执行该类的代码块，再执行构造函数

    static{
        System.out.println("child static code part");
    }

    {      //每次创建对象都执行
        System.out.println("child code part");
    }

    public TestClassChild()
    {
        System.out.println("child consult");
    }

    public TestClassChild(String name)
    {
        this.name = name;
        System.out.println("TestClassChild run aaaaaaaaaa");
    }

    /**
     * 方法重写时，子类方法修饰符范围不能比父类小，否则会报错
     */
   public void run()
    {
        System.out.println("TestClassChild run:"+name);
    }

    @Override
    public void test() {

    }
}
