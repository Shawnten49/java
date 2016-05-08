package com.shuffle.db.pool;

import com.shuffle.db.base.DBbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shawn.xu on 16/5/8.
 */
public class BasePoolConnection implements PoolConnection {
    private DBbean dbBean;             //连接池属性
    private boolean isActive = false;  //连接池是否活跃
    private int totalConnectionCount = 0; //创建的总连接数

    private List<Connection> freeConnectionList = null; //空闲连接池
    private List<Connection> activeConnectionList = null; //活动连接池

    public BasePoolConnection(DBbean dbBean) {
        this.dbBean = dbBean;

        freeConnectionList = new ArrayList<Connection>(dbBean.getMaxFreeConnectionCount());
        activeConnectionList = new ArrayList<Connection>(dbBean.getMaxActiveConnectionCount());

        init();
    }

    /**
     * 初始化数据库连接池
     */
    private void init() {
        if (dbBean.getInitConnectionCount() > dbBean.getMaxFreeConnectionCount()) {
            throw  new RuntimeException("Init Pool Wrong: initConnectionCount should not be bigger than freeConnectionCount ");
        }

        for (int i=0; i<dbBean.getInitConnectionCount(); i++) {
            freeConnectionList.add(i, newConnction());

//            totalConnectionCount++;
        }

        isActive = true;

        checkPool();
    }

    private Connection newConnction() {
        Connection connection = null;
        if (dbBean != null) {
            try {
                Class.forName(dbBean.getDriverName());

                connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUsername(), dbBean.getPassword());

                totalConnectionCount++;

                printf("new");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  connection;
    }

    public synchronized Connection getConnection() {
        Connection connection;

        //活跃池已满,则等待
        int waitTimes = 0;
        while (activeConnectionList.size() >= dbBean.getMaxActiveConnectionCount() && waitTimes<10) {
            try {
                wait(100);
                waitTimes++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (activeConnectionList.size() >= dbBean.getMaxActiveConnectionCount()) {
            throw new RuntimeException("ActiveConnection is Full");
        }

        if (freeConnectionList.size() > 0) {
            connection = freeConnectionList.get(0);

            //移除connection
            freeConnectionList.remove(0);
        } else {
            connection = newConnction();
        }

        activeConnectionList.add(connection);

        return connection;
    }

    public synchronized void releaseConnection(Connection conn) {
        if (conn != null) {
            activeConnectionList.remove(conn);

            //空闲池已满,则释放出来的coonectin直接关闭,否则,放入到空闲池中
            if (freeConnectionList.size() >= dbBean.getMaxFreeConnectionCount()) {
                closeConnection(conn);
                printf("close");
            } else {
                freeConnectionList.add(conn);

                notifyAll(); //唤醒等待线程
            }


        }
    }

    public void destory() {
        for (int i=0; i<freeConnectionList.size(); i++) {
            closeConnection(freeConnectionList.get(i));
        }

        freeConnectionList = null;

        for (int i=0; i<activeConnectionList.size(); i++) {
            closeConnection(activeConnectionList.get(i));
        }

        activeConnectionList = null;

        isActive = false;
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                totalConnectionCount--;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void checkPool() {
        if (dbBean.isCheckPool()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    String log = String.format("Free:%d,Active:%d,Total:%d", freeConnectionList.size(), activeConnectionList.size(), totalConnectionCount);
                    System.out.println(log);
                }
            }, 10, 10);
        }
    }

    public void printf(String type) {
        String log = String.format("-%s-Free:%d,Active:%d,Total:%d",type, freeConnectionList.size(), activeConnectionList.size(), totalConnectionCount);
        System.out.println(log);
    }

}
