package com.shuffle.db.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn.xu on 16/5/8.
 * 启动加载数据配置信息
 */
public class DBInitInfo {

    private static List<DBbean> list = new ArrayList<DBbean>();

    //模拟加载数据配置
    static {
        DBbean dBbean = new DBbean();
        dBbean.setPoolName("mysql01");
        dBbean.setDriverName("com.mysql.jdbc.Driver");
        dBbean.setUrl("jdbc:mysql://localhost:3306/shawn?useSSL=false");
        dBbean.setUsername("root");
        dBbean.setPassword("root");
        dBbean.setIdle(false);
        dBbean.setMaxFreeConnectionCount(10);
        list.add(dBbean);

    }

    public static List<DBbean> getBeans() {
        return list;
    }
}
