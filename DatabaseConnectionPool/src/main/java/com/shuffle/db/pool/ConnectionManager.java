package com.shuffle.db.pool;

import com.shuffle.db.base.DBInitInfo;
import com.shuffle.db.base.DBbean;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn.xu on 16/5/8.
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

    private void init() {
        for (int i=0; i< DBInitInfo.getBeans().size(); i++) {
            DBbean dbBean = DBInitInfo.getBeans().get(i);

            poolMap.put(dbBean.getPoolName(), new BasePoolConnection(dbBean));
        }
    }

    public PoolConnection getPool(String poolName) {
        PoolConnection poolConnection = poolMap.get(poolName);

        if (poolConnection == null) {
            throw new RuntimeException("No Pool Named:" + poolName);
        }

        return poolConnection;
    }

    public Connection getConnection(String poolName) {
        PoolConnection poolConnection = getPool(poolName);

        return  poolConnection.getConnection();
    }

    public void release(String poolName, Connection connection) {
        PoolConnection poolConnection = getPool(poolName);

        poolConnection.releaseConnection(connection);
    }

    public void destory() {
        for (int i=0; i< DBInitInfo.getBeans().size(); i++) {
            DBbean dbBean = DBInitInfo.getBeans().get(i);

            getPool(dbBean.getPoolName()).destory();
        }
    }

    public void destory(String poolName) {
        getPool(poolName).destory();
    }
}

