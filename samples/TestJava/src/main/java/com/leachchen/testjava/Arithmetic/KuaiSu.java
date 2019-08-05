package com.leachchen.testjava.Arithmetic;

/**
 * ClassName:   KuaiSu.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/8/5 13:40
 **/
public class KuaiSu {
    public static void main(String[]args)
    {
        int []arrayData = {2,3,1,33,8,7,0,6,19};
        sortData(arrayData);
    }


    /**
     * 双层循环，假设第一个值是最小的，依次和其后面的值比较，若后面的值有比假设的这个最小的值还小，则把这个值作为最小值，继续后面的值比较，最终这一次循环可以找出最小的值，然后后第一个假设最小的值进行交互，循环多次即完成排序
     *
     * **/
    private static void sortData(int []data)
    {
        /*int min;
        int temp;
        for (int i =0;i<data.length;i++)
        {
            min = i;

            for(int j = i+1;j<data.length;j++)
            {
                if(data[j] < data[min])
                {
                    min = j;
                }
            }

            if(i != min)
            {
                temp = data[i];
                data[i] = data[min];
                data[min] = temp;
            }
        }*/


        int max;
        int temp;
        for(int i = 0;i<data.length;i++)
        {
            max = i;
            for (int j = i;j<data.length;j++)
            {
                if(data[j] > data[max])
                {
                    max = j;
                }
            }

            if(i != max)
            {
                temp = data[i];
                data[i] = data[max];
                data[max] = temp;
            }
        }

        for(int i=0;i<data.length;i++)
        {
            System.out.print(data[i]+",");
        }

    }

}
