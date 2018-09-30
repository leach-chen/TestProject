package com.leachchen.testjava;

/**
 * ClassName:   TestInterface.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/18 14:17
 **/
public interface TestInterface extends A,B{
     String name ="aa";  //默认为静态常量类型 public static final
     void test();   //默认是public abstract
}
interface A
{

}

interface B
{

}