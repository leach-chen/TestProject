package com.leachchen.testjava.Arithmetic;

/**
 * ClassName:   ChaRu.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/8/4 23:28
 **/
public class ChaRu {
    public static void main(String []args)
    {
        int []arrayData = {2,3,1,33,8,7,0,6,19};
        sortData(arrayData);
    }

    /**
     * 插入排序，双层循环，假设前n位数已经排好序，第n+1位是待排序元素，保存为temp，n+1位与前n位从后一次比较，降序为例若n+1大于第n位，
     * n+1位与n位互换位置，此时最大的下沉至最后一位，第n位是待temp替换的元素，刚与第n位比较完，现在第n-1位与temp进行比较，若temp小于n-1
     * 则用temp替换掉第n位，若temp比n-1大，则继续执行上面的循环
     * @param data
     */
    private static void sortData(int []data)
    {
        int temp;
        for (int i = 0;i < data.length;i++)
        {
            temp = data[i]; //待插入数据
            int j;
            for (j = i - 1; j>=0;j--)
            {
                if(data[j] < temp)
                {
                    data[j+1] = data[j];
                }else
                {
                    break;
                }
            }
            data[j+1] = temp;
        }

        for(int i=0;i<data.length;i++)
        {
            System.out.print(data[i]+",");
        }
    }
}
