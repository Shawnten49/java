package com.shuffle.db.pool;

import java.sql.Connection;

/**
 * Created by shawn.xu on 16/5/8.
 * 连接池接口
 */
public interface PoolConnection {

    /**
     * 获取Connection
     * @return
     */
    Connection getConnection();

    /**
     * 释放connection
     * @param conn
     */
    void releaseConnection(Connection conn);

    /**
     * 销毁连接池
     */
    void destory();

    /**
     * 连接池是否有效
     * @return
     */
    boolean isActive();

    /**
     * 检查连接池(可以改成 超时释放连接池)
     */
    void checkPool();

    /**
     * 打印信息
     * @param type
     */
    void printf(String type);
}
