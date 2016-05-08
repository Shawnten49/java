package com.shuffle.db.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn.xu on 16/5/8.
 */
public class DBInitInfo {

    private static List<DBbean> list = new ArrayList<DBbean>();

    //模拟加载数据配置
    static {
        DBbean dBbean = new DBbean();
        dBbean.setPoolName("mysql01");
        dBbean.setDriverName("com.mysql.cj.jdbc.Driver");
        dBbean.setUrl("jdbc:mysql://localhost:3306/shawn?useSSL=false");
        dBbean.setUsername("root");
        dBbean.setPassword("root");
        dBbean.setCheckPool(false);
        list.add(dBbean);

    }

    public static List<DBbean> getBeans() {
        return list;
    }
}
