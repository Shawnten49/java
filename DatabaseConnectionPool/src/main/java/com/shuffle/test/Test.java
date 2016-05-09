package com.shuffle.test;

import com.shuffle.db.DBhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:Shawn.Xu
 * Date:2016/5/7
 * Description:
 */
public class Test {

    public static void main(String[] args) {
        for (int i=0; i<200; i++) {
            Thread t1 = Test.getTread();
            t1.start();
        }


//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static Thread getTread() {
        return new Thread(new Runnable() {
            public void run() {
                String poolName = "mysql01";
                String sql = "select * from test where id = 1";
                List<Object> param = new ArrayList<Object>();

                System.out.println(System.currentTimeMillis() + ": begin" );
                Map<String, Object> map = DBhelper.findSimpleResult(poolName, sql, param);

                System.out.println(System.currentTimeMillis() + ":" + map);

            }
        });
    }

}
