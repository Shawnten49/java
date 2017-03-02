package com.shuffle.entity;

import java.util.Date;

/**
 * Author:Shawn.Xu
 * Date:2017/3/2
 * Description:
 */
public class SecKill {
    private long seckillID;

    private String name;

    private int number;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public long getSeckillID() {
        return seckillID;
    }

    public void setSeckillID(long seckillID) {
        this.seckillID = seckillID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecKill{" +
                "seckillID=" + seckillID +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
