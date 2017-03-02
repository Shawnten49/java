package com.shuffle.test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author:Shawn.Xu
 * Date:2016/6/29
 * Description:
 */
public class TreadTest {
    public static void main(String[] args) {
//        testSleep();
//        testWait();
        testLock();
    }


    public static void testSleep() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (Wait.class) {
                        System.out.println("Thread1 is running...");
                        Thread.sleep(1000*2);
                        System.out.println("Thread1 is over....");
                    }

                } catch (Exception e) {

                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (Wait.class) {
                        System.out.println("Thread2 is running...");
                        Thread.sleep(1000*2);
                        System.out.println("Thread2 is over....");
                    }
                } catch (Exception e) {

                }
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void testWait() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Wait.class) {
                    System.out.println("Thread1 is running...");
                    try {
                        Wait.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1 is over...");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Wait.class) {
                    System.out.println("Thread2 is running...");

                    Wait.class.notify();

                    System.out.println("Thread2 is over....");
                }
            }
        });

        thread1.start();
        thread2.start();
    }


    public static void testLock() {
        final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   readWriteLock.readLock().lock();
                    System.out.println("Thread1 is running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1 is over...");
                }finally {
                   readWriteLock.readLock().unlock();
               }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLock.readLock().lock();
                    System.out.println("Thread2 is running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread2 is over...");
                }finally {
                    readWriteLock.readLock().unlock();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readWriteLock.writeLock().lock();
                    System.out.println("Thread3is running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread3 is over...");
                }finally {
                    readWriteLock.writeLock().unlock();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}



class Wait {

}