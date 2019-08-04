package com.leachchen.testjava.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:   TestClass.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/8/3 10:16
 **/
public class TestClass {

    public static void main(String[]args)
    {
        int []data = {6, 4, -3, 5, -2, -1, 0, 1, -9};
        sortData(data);
    }

    private static void sortData(int []data)
    {
        int tempValue;
        int position;
        for(int i =0;i<data.length / 2;i++)
        {
            if(data[i] > 0)continue;
            position = data.length - 1 - i;
            while(data[position]<0)
            {
                position--;
            }
            tempValue = data[i];
            data[i] = data[position];
            data[position] = tempValue;
        }
    }

    public static void handData()
    {
        String str = "{ ‘A’: 1, ‘B.A’: 2, ‘B.B’: 3, ‘CC.D.E’: 4, ‘CC.D.F’: 5}";
        Map<String,String> map = new HashMap<>();
        String []strList = str.split(",");

    }


}
