package com.shuffle.db.pool;

import com.shuffle.db.base.DBInitInfo;
import com.shuffle.db.base.DBbean;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn.xu on 16/5/8.
 * 连接池管理对象
 */
public class ConnectionManager {

    Map<String, PoolConnection> poolMap = new HashMap<String, PoolConnection>();

    public ConnectionManager() {
        init();
    }


    //单例
    public static ConnectionManager getInstance() {
        return Singtole.instance;
    }

    private static class Singtole {
        private static ConnectionManager instance = new ConnectionManager();
    }

    /**
     * 初始化
     */
    private void init() {
        for (int i=0; i< DBInitInfo.getBeans().size(); i++) {
            DBbean dbBean = DBInitInfo.getBeans().get(i);

            poolMap.put(dbBean.getPoolName(), new BasePoolConnection(dbBean));
        }
    }

    /**
     *得到连接池
     * @param poolName
     * @return
     */
    public PoolConnection getPool(String poolName) {
        PoolConnection poolConnection = poolMap.get(poolName);

        if (poolConnection == null) {
            throw new RuntimeException("No Pool Named:" + poolName);
        }

        return poolConnection;
    }

    /**
     * 得到指定连接池的connection
     * @param poolName
     * @return
     */
    public Connection getConnection(String poolName) {
        PoolConnection poolConnection = getPool(poolName);

        return  poolConnection.getConnection();
    }

    /**
     * 释放指定的conneciton
     * @param poolName
     * @param connection
     */
    public void release(String poolName, Connection connection) {
        PoolConnection poolConnection = getPool(poolName);

        poolConnection.releaseConnection(connection);
    }

    /**
     * 销毁所有的连接池
     */
    public void destory() {
        for (int i=0; i< DBInitInfo.getBeans().size(); i++) {
            DBbean dbBean = DBInitInfo.getBeans().get(i);

            getPool(dbBean.getPoolName()).destory();
        }
    }

    /**
     * 销毁指定的连接池
     * @param poolName
     */
    public void destory(String poolName) {
        getPool(poolName).destory();
    }
}

