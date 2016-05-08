package com.shuffle.db.pool;

import java.sql.Connection;

/**
 * Created by shawn.xu on 16/5/8.
 */
public interface PoolConnection {

    Connection getConnection();

    void releaseConnection(Connection conn);

    void destory();

    boolean isActive();

    void checkPool();

    public void printf(String type);
}
