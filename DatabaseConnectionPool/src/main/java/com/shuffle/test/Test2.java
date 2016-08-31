package com.shuffle.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:Shawn.Xu
 * Date:2016/5/13
 * Description:
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++)
            exec.execute(new MyThread(i));

        exec.shutdown();
    }
}

class MyThread implements Runnable {
    private int count = 1, number;

    public MyThread(int num) {
        number = num;
        System.out.println("Create Thread-" + number);
    }

    public void run() {
//        while (true) {
            System.out.println("Thread-" + number + " run " + count+" time(s)");
//            if (++count == 3)
//                return;
//        }
    }
}