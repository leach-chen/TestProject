package com.leachchen.testjava;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:   TestThread.java
 * Description:
 * Author :     leach.chen
 * Date:        2018/9/17 17:40
 **/
public class TestThread {

    private int i = 0;
    private List<String> dataList = new ArrayList<>();

    private boolean isRunA = true;

    public TestThread() {
        dataList.add("A");
        dataList.add("B");
        dataList.add("C");
        dataList.add("D");
        dataList.add("E");
        dataList.add("F");
        dataList.add("G");
        dataList.add("H");
        dataList.add("I");
        dataList.add("J");
        dataList.add("K");
        dataList.add("L");
        dataList.add("M");
        dataList.add("N");
        dataList.add("O");
        dataList.add("P");
        dataList.add("Q");
        dataList.add("R");
        dataList.add("S");
        dataList.add("T");
        dataList.add("U");
        dataList.add("V");
        dataList.add("W");
        dataList.add("X");
        dataList.add("Y");
        dataList.add("Z");
    }

    public void start() {
        A a = new A();
        B b = new B();

        a.start();
        b.start();
    }

    class A extends Thread {
        public void run() {
            System.out.print("aa");
            int count = 0;
            while (i < 52) {
                synchronized (dataList) {
                    if (isRunA) {
                        i++;
                        count++;
                        if (count <= 2) {
                            System.out.print("" + i);
                        }

                        if (count == 2) {
                            count = 0;
                            try {
                                isRunA = false;
                                dataList.notify();
                                dataList.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    class B extends Thread {
        public void run() {
            System.out.print("bb");
            int count = 0;
            int position = 0;
            while (i <= 52) {
                synchronized (dataList) {
                    if (!isRunA) {
                        count++;
                        if (count == 1 && position < dataList.size()) {
                            System.out.print(dataList.get(position));
                            count = 0;
                        }
                        position++;

                        try {
                            isRunA = true;
                            dataList.notify();
                            dataList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
