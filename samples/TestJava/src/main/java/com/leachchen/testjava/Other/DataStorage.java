package com.leachchen.testjava.Other;

/**
 * ClassName:   DataStorage.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/30 11:50
 **/


public class DataStorage {


    public int data; //成员变量的引用及值存储在堆中的对象里面

    public static final int data1 = 1; //存储在常量区


    public void setData(int b) //形参b是局部变量，且是基础类型值及引用存储在栈上,当setData执行完后b就会从栈中消失
    {
        int a = 0; //变量的引用及值存储在栈中，a是指向值为0的地方
        int c = 0;//变量的引用及值存储在栈中，a是指向值为0的地方,与a指向的值为同一个值
        String str = new String("aa"); //字符串对象的引用存储在栈中，对象存储在堆上，字符串存储在常量区
    }

    /**
     * public class Test {    

         public static void main(String[] args)    

         {  int a1=1;  

             int b1=1;  

             int c1=2;  

             int d1=a1+b1;  

             Integer a = 1;    

             Integer b = 2;    

             Integer c = 3;    

             Integer d = 3;    

             Integer e = 321;    

             Integer f = 321;    

             Long g = 3L;    

             System.out.println(a1==b1);   //true  结果1    

             System.out.println(c1==d1);   //true  结果2  

             System.out.println(c==d);   //true  结果3     

             System.out.println(e==f);   //false  结果4       

         }    

     }    

      分析：结果1：a1==b1如上面所述,会在栈 中开辟存储空间存放数据。

               结果2：首先它会在栈 中创建一个变量为c1的引用，然后查找有没有字面值为2的地址，没找到，就开辟一个存放2这个字面值的地址，然后将c1指向2的地址,d1为两个字面值相加也为2， 由于在栈中已经有2这个字面值，便将d1直接指向2的地址。这样，就出现了c1与d1同时均指向3的情况。

      

             在分析下面结果以前让我们先对Java自动拆箱和装箱做个了结：在自动装箱时，把int变成Integer的时候，是有规则的，当你的int的值在-128-IntegerCache.high(127) 时，返回的不是一个新new出来的Integer对象，而是一个已经缓存在堆 中的Integer对象，（我们可以这样理解，系统已经把-128到127之 间的Integer缓存到一个Integer数组中去了，如果你要把一个int变成一个Integer对象，首先去缓存中找，找到的话直接返回引用给你就 行了，不必再新new一个），如果不在-128-IntegerCache.high(127)
     时会返回一个新new出来的Integer对象。

      

              结果3：由于3是在范围内所以是从缓存中取数据的，c和d指向同一个对象，结果为true;

              结果4：由于321不是在范围内所以不是从缓存中取数据的而是单独有new对象，e和f并没有指向同一个对象，结果为false;

     */
}
