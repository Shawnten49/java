package classloader;

import java.lang.reflect.Method;

/**
 * Author:Shawn.Xu
 * Date:2016/9/1
 * Description:
 */
public class Main {
    public static void main(String[] args) throws Exception{
        MyClassLoader classLoader1 = new MyClassLoader();
        classLoader1.setClassPath("F:\\shuffle\\github\\java2\\trunk\\log4jSocket\\target\\classes\\");

        MyClassLoader classLoader2 = new MyClassLoader(null);
        classLoader2.setClassPath("F:\\shuffle\\github\\java2\\trunk\\log4jSocket\\MyTarget\\classes\\");

        final Class clazz1 = classLoader1.loadClass("clients.ClientToLog4j1");
        System.out.println("----------clazz1 getClassLoader:" + clazz1.getClassLoader());

        final Class clazz2 = classLoader2.loadClass("clients.ClientToLog4j2");
        System.out.println("----------clazz2 getClassLoader:" + clazz2.getClassLoader());


//        Method main2 = clazz2.getMethod("testLog", null);
//        main2.invoke(clazz2.newInstance(), null);

        new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   Method main1 = clazz1.getMethod("testLog", null);
                   main1.invoke(clazz1.newInstance(), null);
               } catch (Exception e) {
                   e.printStackTrace();
               }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Method main2 = clazz2.getMethod("testLog", null);
                    main2.invoke(clazz2.newInstance(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();



    }
}
