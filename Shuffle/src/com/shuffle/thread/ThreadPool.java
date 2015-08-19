package com.shuffle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

   public class ThreadPool {
 
        private final static String poolName = "mypool";
 
        static private ThreadPool threadFixedPool = new ThreadPool(2);
 
       private ExecutorService executor;
 
      static public ThreadPool getFixedInstance() {
 
           return threadFixedPool;
 
       }
 
    private ThreadPool(int num) {
    
           executor = Executors.newFixedThreadPool(num, new DaemonThreadFactory(poolName));
 
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
 
           for (int i = 0; i < 10; i++) {
 
             ThreadPool.getFixedInstance().execute(new MyRunnable());
 
           }
 
           try {
 
                    Thread.sleep(2000);
 
                    System.out.println("Process end.");
 
           } catch (InterruptedException e) {
 
                    e.printStackTrace();
 
           }
 
}
 
}
