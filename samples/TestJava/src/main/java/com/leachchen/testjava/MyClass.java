package com.leachchen.testjava;



/**
 * 1.静态方法重写是无效的，调用的还是父类的方法，成员变量重写也是无效的，调用的还是父类的成员变量值,非私有成员方法可发生重写
 * 2.成员变量默认为default(friendly)类型，允许同一个包内的类访问
 * 3.抽象类无法被实例化
 * 4.
 */

public class MyClass {

    static String str = "abc";
    static char[]a = {'a','b','c'};

    public static void change(String st,char []aa)
    {
        str = "bbb";
        aa[0] = 'c';
    }

    public static void main(String []args)
    {
        /*TestPart testPart = new TestPart();
        testPart.testPart2();*/

        /*TestClassA testClassA = new TestClassB();
        testClassA.run();
        TestClassB testClassB = new TestClassB();
        System.out.println("aa:"+testClassA.name+"  bb:"+testClassB.name);*/

        //change("ccc",a);
        //System.out.println("str:"+str+"  aa:"+a[0]);

        TestThread  testThread = new TestThread();
        testThread.start();
    }

}
