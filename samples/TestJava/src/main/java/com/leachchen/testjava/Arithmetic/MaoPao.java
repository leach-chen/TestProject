package com.leachchen.testjava.Arithmetic;

/**
 * ClassName:   MaoPao.java
 * Description:
 * Author :     leach.chen
 * Date:        2019/8/4 22:28
 **/
public class MaoPao {

    public static void main(String[]args)
    {
        int []arrayData = {2,3,1,33,8,7,0,6,19};
        sortData(arrayData);
    }

    /**
     * 双层循环，每次完成一层子循环下层或者上浮一个数据，子循环次数也较少一次
     * 时间复杂度(n-1)+(n-2)+..+1=((n-1)+1)*(n-1)=n^2-n/2,比较大时，n/2可以忽略，最差时间复杂度为O(n~2),
     * 当数组本身有序时，增加一个辅助变量，如第一次循环，没有进入条件一次，则数组本身有序，则最优时间复杂度为O(n),空间复杂度增加一*
     * @param data
     */
    private static void sortData(int []data)
    {
        int temp;
        boolean isRun = false;
        /*for(int i = 0; i<data.length;i++)
        {
            //下沉方式,升序
            for(int j =0; j < data.length - i - 1;j++)
            {
                if(data[j] > data[j+1])
                {
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j + 1] = temp;
                    isRun = true;
                }
            }
            if(!isRun)
            {
                break;
            }
        }*/

        /*for(int i =0;i<data.length;i++)
        {
            //下沉方式，降序
            for(int j =0;j < data.length - i - 1;j++)
            {
                if(data[j] < data[j+1])
                {
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                    isRun = true;
                }
            }
            if(!isRun)
            {
                break;
            }
        }*/

        for(int i = 0; i<data.length;i++)
        {
            //上浮方式,升序
            for(int j = data.length - 1; j>i;j--)
            {
                if(data[j] < data[j-1])
                {
                    temp = data[j-1];
                    data[j-1] = data[j];
                    data[j]=temp;
                    isRun = true;
                }
            }

            if(!isRun)
            {
                break;
            }
        }

        /*for(int i = 0; i <data.length;i++)
        {
            //上浮方式，降序
            for (int j = data.length - 1;j>i;j--)
            {
                if(data[j] > data[j-1])
                {
                    temp = data[j-1];
                    data[j-1] = data[j];
                    data[j]=temp;
                    isRun = true;
                }
            }

            if(!isRun)
            {
                break;
            }
        }*/

        for(int i=0;i<data.length;i++)
        {
            System.out.print(data[i]+",");
        }
    }


}
