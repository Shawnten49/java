package com.shuffle.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool2 {
 
         private final static String poolName = "mypool";
 
         static private ThreadPool2 threadFixedPool = new ThreadPool2(2);
 
         public ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(2);
 
         private ExecutorService executor;
 
  
 
         static public ThreadPool2 getFixedInstance() {
 
                   return threadFixedPool;
 
         }
 
         private ThreadPool2(int num) {
 
                   executor = new ThreadPoolExecutor(2, 4,60,TimeUnit.SECONDS, queue,new DaemonThreadFactory
 
(poolName), new ThreadPoolExecutor.AbortPolicy());
 
         }
 
         public void execute(Runnable r) {
 
                   executor.execute(r);
 
         }
 
         
 
         public static void main(String[] params) {
 
                   class MyRunnable implements Runnable {
 
                            public void run() {
 
                                     System.out.println("OK!");
 
                                     try {
 
                                               Thread.sleep(10);
 
                                     } catch (InterruptedException e) {
 
                                               e.printStackTrace();
 
                                     }
 
                            }
 
                   }
 
                   int count = 0;
 
                   for (int i = 0; i < 10; i++) {
 
                            try {
 
                                     ThreadPool2.getFixedInstance().execute(new MyRunnable());
 
                            } catch (RejectedExecutionException e) {
 
                                     e.printStackTrace();
 
                                     count++;
 
                            }
 
                   }
 
                   try {
 
                            System.out.println("queue size:" + ThreadPool2.getFixedInstance().queue.size());
 
                            Thread.sleep(2000);
 
                   } catch (InterruptedException e) {
 
                            e.printStackTrace();
 
                   }
 
                   System.out.println("Reject task: " + count);
 
         }
 
}
