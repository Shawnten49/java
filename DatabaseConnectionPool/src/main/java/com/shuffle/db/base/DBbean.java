package com.shuffle.db.base;

/**
 * Created by shawn.xu on 16/5/8.
 * 数据库连接属性
 */
public class DBbean {
    /**
     *数据库连接池名称
     */
    private String poolName;

    /**
     * 驱动名称
     */
    private String driverName;

    /**
     * 数据库连接url
     */
    private String url;

    /**
     * 账户用户名
     */
    private String username;

    /**
     * 账户密码
     */
    private String password;

    /**
     * 初始化连接数
     */
    private int initConnectionCount = 3;

    /**
     * 空闲池,最小连接数
     */
    private int minFreeConnectionCount = 1;

    /**
     * 空闲池,最大连接数
     */
    private int maxFreeConnectionCount = 5;

    /**
     * 最大的使用连接数
     */
    private int maxActiveConnectionCount = 10;

    /**
     * 启动空闲监控
     */
    private boolean isIdle = true;

    /**
     * 空闲时间(超过时间,释放)
     */
    private int maxIdleTime = 10;


    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitConnectionCount() {
        return initConnectionCount;
    }

    public void setInitConnectionCount(int initConnectionCount) {
        this.initConnectionCount = initConnectionCount;
    }

    public int getMinFreeConnectionCount() {
        return minFreeConnectionCount;
    }

    public void setMinFreeConnectionCount(int minFreeConnectionCount) {
        this.minFreeConnectionCount = minFreeConnectionCount;
    }

    public int getMaxFreeConnectionCount() {
        return maxFreeConnectionCount;
    }

    public void setMaxFreeConnectionCount(int maxFreeConnectionCount) {
        this.maxFreeConnectionCount = maxFreeConnectionCount;
    }

    public int getMaxActiveConnectionCount() {
        return maxActiveConnectionCount;
    }

    public void setMaxActiveConnectionCount(int maxActiveConnectionCount) {
        this.maxActiveConnectionCount = maxActiveConnectionCount;
    }
}
