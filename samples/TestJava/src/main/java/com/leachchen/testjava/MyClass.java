package com.leachchen.testjava;


/**
 * 1.静态方法重写是无效的，调用的还是父类的方法，成员变量重写也是无效的，调用的还是父类的成员变量值,非私有成员方法可发生重写,不带修饰符的方法可以重写
 * 2.成员变量默认为default(friendly)类型，允许同一个包内的类访问
 * 3.抽象类无法被实例化
 * 4.匿名内部类new B（）{}
 * 5.接口--》
 *      接口里面可以定义成员变量默认为静态常量类型；
 *
 *      接口默认也是抽象的，也不能被实例化；
 *      接口里面的方法不能有方法体，默认是public abstract，也只能是这个类型；
 *      接口只能继承接口，不能实现（implements）；
 *      接口可以实现多继承
 * 7.一个类继承了一个类，又实现了一个接口，被继承的类和接口里都有同样的成员变量，如果这个类里面没有这个成员变量，则无法使用该成员变量，因为不确定用父类的还是接口的
 * 8.子类有参构造函数，父类无参构造函数，在新建子类实例时，还是会执行父类无参构造函数
 * 9.类的方法里也可以定义一个局部类。
 * 10.一个类定义为内部类，则其可以访问它的外部类的私有公有方法，成员变量，若该类为静态内部类，则只能访问它的外部类的静态方法及成员变量
 * 11.父类子类有同名成员变量，子类改变成员变量的值，父类不会变
 * 12.JAVA初始化顺序，父类静态成员初始化--》父类静态代码块--》子类静态成员初始化--》子类静态代码块--》父类代码块--》父类的构造方法--》子类的代码块--》子类的构造方法
 * 13.一个类文件里面，public 的外部类只能有一个，不带标记的类可以有多个
 * 14.finally中有return会覆盖try catch中的return的值，没有的话则用try，catch中return的值。执行顺序是先执行try里面代码再执行catch再执行finally,但是finally中的代码优先于try catch中的return语句执行
 * 15.静态static不能修饰局部变量
 * 16.方法重写时(extend,implements)，子类方法修饰符范围不能比父类小，否则会报错
 * 17.打印对象时，重写了toString方法，则调用的toString方法
 * 18.单例的构造函数可以是私有的，这样可以防止外部new这个类
 * 19.重载，方法名相同，参数类型或个数必须不同，返回类型及修饰符可相同可不相同
 * 20.父类子类命名相同的方法，要么都为静态，要么都不是静态，为静态是隐藏，非静态是重写
 * 21.定义局部变量必须赋值初始化
 * 22.标识符用来给变量，类，方法名等命名，标识符只能是字母数字下划线及$符号，不能以数字开头，不能为关键字，区分大小写（命名规则）
 * 23.匿名类直接在方法中new
 * 24.对于字符串，对象的引用str存储在栈中，直接引号定义的存储在常量池中，运行时new出来的(new String("aa"))，存储在堆上，对于equals相等的字符串，常量区只有一份，堆中有多份,String str = new String("aa");
 *    成员变量存储在堆的对象里，局部变量存储在栈中
 *    对于基础类型的变量和常量，变量和引用存储在栈中，常量存储在常量池中
 * 25.父类子类重写的方法，返回值一定要一致，子类该方法的修饰符要大于等于父类
 * 26.A类里面new B类对象，若B类与A类在不同的包中，且B类不是public类，则不能new B类,在同一个包中不是public的类也可以new
 * 27.new 子类给父类对象时，父类没有重写该方法，则父类不能直接通过对象调用该方法，得强转为子类对象
 * 28.类方法为非private时，都可以被类对象调用，如果是内部类的方法，只有public方法可以调用
 * 29.继承具有传递性，子类可以向最上层转型
 * 30.构造函数不能被继承，只能显示或者隐式的调用
 * 31.接口声明（用的时候）用implements，定义用interface,java 1.8以后在接口定义里面允许有方法实现
 * 32.编译器将java源代码编译成字节码class文件，然后由JVM进行加载，执行引擎把字节码转换为可执行代码，再把可执行代码转为机器码，由底层的操作系统完成执行
 * 33.JVM主要分三个区，堆、栈、方法区，堆存放对象，线程共享的，栈存放基本类型数据和对象的引用，是线程私有的，方法区存放类信息、常量、静态变量、编译的字节码，是线程共享的，垃圾回收主要针对堆区
 *    JAVA 虚拟机对应方法的调用是采用栈帧，调用入栈，完成后出栈，出栈就回收了资源，而不是由GC来释放
 * 34.内部类的访问属性有多种，可以理解为类的方法
 * 35.Stack 栈先进后出，队列queue，先进先出
 * 36.类是单继承，但是接口可以多继承
 * 37.同一类或者父子类中不允许除了返回类型不一样，其它都一样的方法定义
 * 38.外部类只能为public，abstract，final，default，内部类可以为任何修饰符，内部类为静态时，就不需要外部类的应用了
 * 39.static不能应用于局部变量，只占用一份存储区域，类的静态成员会随类的加载而加载
 */

public class MyClass {

    static String str = "abc";  //abc在常量区
    static String str1 = "aaa";  //abc在常量区
    static char[]a = {'a','b','c'};
    static int bb = 2;

    /**
     * @param st 基本数据类型 是值传递，不影响成员变量的值
     * @param aa 数组是引用传递，影响成员变量的值
     *
     *  函数参数传递是按栈中的值传递的，基本数据类型栈中就是存储的实际的值，引用类型存储的是堆中的地址，参数传递都会新建过一个对应的变量
     */
    public static void change(String st,char []aa,int bb)
    {
        st = "bbb";  //新建过一个st，传值时默认指向abc，重新赋值这个st指向bbb
        aa[0] = 'c';   //新建过一个数组，但是还是指向堆中的同一个数据
        bb = 10;    //新建过一个变量在栈中，传值时默认是2，重新赋值为10
    }



    public static void main(String []args)
    {
        StringBuffer str1 = new StringBuffer("aa");
        StringBuffer str2 =  new StringBuffer("bb");;

        TestPart testPart = new TestPart();
        testPart.testPart15(str1,str2);
        System.out.println(str1+" "+str2);

/*      change(str,a,2);
        System.out.println(str);
        System.out.println(a);
        System.out.println(bb);*/


/*        try {
            testPart.testPart12();
        }catch (Exception e)
        {
            System.out.println("ccccccccc");
        }*/
 /*       testPart.testPart14();

      TestClassParent testClassParent = new TestClassParent();

        testClassParent.run();*/


        //TestClassChild testClassChild = new TestClassChild();
        //System.out.println("aa:"+testClassParent.name+"  bb:"+testClassChild.name);

        //change("ccc",a);
        //System.out.println("str:"+str+"  aa:"+a[0]);

        /*TestThread  testThread = new TestThread();
        testThread.start();*/

        //String[]s = new String[10];
        //System.out.println(""+(1==1));

        //Demo demo = new Demo(1);
        //System.out.println(demo);

        //new B();
    }

    /*    class A
    {

    }

    class B extends A{

    }

    class C extends A {
        B b = null;
        C c;
        c =(C)b;
    }*/

    static class Demo
    {
        private int data;
        public Demo(int data)
        {
            this.data = data;
        }

        public String toString()
        {
            return "data="+data;
        }
    }

    static class A
    {
        public A()
        {
            System.out.println("A");
        }

        public A(String s)
        {
            System.out.println("A-"+s);
        }
    }


    static class B extends A
    {
        public B()
        {
            this("B");
        }

        public B(String s)
        {
            super(s);
            System.out.println(s);
        }
    }
}
