package com.leachchen.testjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

/**
 * ClassName:   testPart.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 15:03
 **/
public class TestPart{

    /**
     * try catch finally执行
     * finally中有return会覆盖try catch中的return的值，没有的话则用try，catch中return的值。执行顺序是先执行try里面代码再执行catch再执行finally,但是finally中的代码优先于try catch中的return语句执行
     *
     */
    public int testPart1()
    {
        int a = 0;
        try
        {
            a = 10 / 0;
            System.out.println("aa:"+a);
            return a;
        }catch (Exception e)
        {
            a = 20;
            System.out.println("bb:"+a);
            return a;
        }finally {
            a = 30;
            System.out.println("cc:"+a);
            return a;
        }
    }

    /**
     * 字符串截取
     */
    public void testPart2()
    {
       String str = "C://myFile/a/b/c/d/a.text";
       System.out.println(str.substring(str.indexOf("d")+1+1,str.length()));
    }

    /**
     * 文件递归获取
     * 1.先获取该目录下的文件列表
     * 2.如果是文件夹则递归获取子文件夹里面的数据
     */
    public void testPart3(String path)
    {
        File file = new File(path);
        File []fileList = file.listFiles();
        System.out.println("path:"+path+" size:"+fileList.length);
        for (int i = 0 ; i < fileList.length;i++)
        {
            System.out.println(fileList[i].getPath());
            if(fileList[i].isDirectory())
            {
                testPart3(fileList[i].getPath());
            }
        }
    }

    /**
     * 文件拷贝
     * 1.打开源文件
     * 2.打开输入流，读取文件数据
     * 3.打开输出流，将读取到的文件输出到硬盘
     */
    public void testPart4(String sourcePath,String destPath)
    {
        File file = new File(sourcePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);    //从硬盘读取
            FileOutputStream fileOutputStream = new FileOutputStream(new File(destPath)); //输出到硬盘
            byte []data = new byte[1024];
            int readCount = 0;
            while ((readCount = fileInputStream.read(data)) != -1)
            {
                fileOutputStream.write(data,0,readCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求
     * 1.创建URL
     * 2.通过URL打开输入流openConnection
     * 3.设置相关请求参数
     * 4.getResponseCode状态码为200时，通过httpURLConnection获取到输入流，读取输入流里面的数据
     */
    public static void testPart5()
    {
        try {
            URL url = new URL("https://www.baidu.com/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000 * 5);
            if(httpURLConnection.getResponseCode() == 200)
            {
                int count = 0;
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[]data = new byte[1024 * 4];
                String str = "";
                while ((count = inputStream.read(data)) != -1)
                {
                    str = str + new String(data);
                }
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }


    /**
     * list遍历删除
     * 1：for循环删除时，会导致元素前移，导致数据漏掉，for(foreach)而且可能会报ConcurrentModificationException异常
     * 2：可用迭代器删除法
     */
    public void testPart6()
    {
        List<String> list = new ArrayList<>();
        list.add("1"); list.add("1");list.add("2");list.add("3");
        list.add("1");list.add("2");list.add("3");
        list.add("1");list.add("2");list.add("3");
        list.add("1");list.add("2");list.add("3");
        System.out.println("list size1:"+list.size());
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext())
        {
            String str = iterator.next();
            if(str.equals("1"))
            {
                iterator.remove();
            }
        }

/*
        //元素可能漏掉
        for(int i = 0; i < list.size();i++)
        {
            if(list.get(i).equals("1"))
            {
                list.remove(i);
            }
        }*/

/*
        Exception in thread "main" java.util.ConcurrentModificationException
        for (String s : list)
        {
            if(s.equals("1"))
            {
                list.remove(s);
            }
        }*/

        System.out.println("list size2:"+list.size());
    }

    /**
     * Map遍历
     */
    public void testPart7()
    {
        Map<Integer,String> stringMap = new HashMap<>();
        stringMap.put(1,"A");
        stringMap.put(2,"B");
        stringMap.put(3,"C");
        stringMap.put(4,"D");
        stringMap.put(5,"E");

        for(Map.Entry<Integer,String> map : stringMap.entrySet())
        {
            System.out.println("key:"+map.getKey()+" value:"+map.getValue());
        }
    }

    /**
     * 数组平均值，最大最小值,排序为冒泡排序法
     */
    public void testPart8()
    {
        int []array = {1,3,2,4,6,5,8,7,9,10};
        int sum = 0;
        int max;
        int min;

        for (int i =0; i < array.length;i++)
        {
            sum = sum+array[i];
        }
        System.out.println("sumsplit:"+sum/10.0);


        for(int i = 0; i < array.length;i++)
        {
            for(int j = 0; j < array.length - i - 1;j++)
            {
                int tempValue = array[j];
                if(array[j] >= array[j+1])
                {
                    array[j] = array[j+1];
                    array[j+1] = tempValue;
                }
            }
        }

        for(int i = 0; i < array.length;i++)
        {
            System.out.println(array[i]);
        }
    }

    /**
     * 控制台输入
     */
    public void testPart9()
    {
        List<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        list.add(scanner.nextLine());
        list.add(scanner.nextLine());

        for(int i = 0; i < list.size();i++)
        {
            System.out.println(list.get(i));
        }
    }

    public void testPart10()
    {
        System.out.println("aa:"+10/8); //整除
        System.out.println("bb:"+10%8); //取余

        Integer a1 = 127;
        Integer a2 = 127;
        System.out.println((a1==a2));
        //a3 = Integer.valueOf(128);,超过127的值会新建一个对象进行存储，否则使用内存中的那个对象
        Integer a3 = 128;
        Integer a4 = 128;
        System.out.println((a3==a4));

        String str1 = "java";
        String str2 = "blog";
        String str3 = "javablog";
        String str4 = "javablog";
        String str = str1+str2;
        System.out.println("aa:"+str3==str);
        System.out.println("bb:"+str3.compareTo(str4));
        System.out.println("cc:"+str3.equals(str4));
        System.out.println("dd:"+str3.concat(str4));

        int count = 0;
        for(int i = 0; i < 100;i++)
        {
            count = count++;    //先将count值缓存起来，然后count加1，然后将缓存的值给count
        }
        System.out.println("count:"+count);

        for(int i = 0; i < 10;++i)
        {
            System.out.print(i);
        }
    }

    public void testPart11()
    {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));

    }
}
