package com.shuffle.entity;

import java.util.Date;

/**
 * Author:Shawn.Xu
 * Date:2017/3/2
 * Description:
 */
public class SuccessSecKill {
    private  long userPhone;

    private int stat;

    private Date createTime;

    private SecKill secKill;

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SecKill getSecKill() {
        return secKill;
    }

    public void setSecKill(SecKill secKill) {
        this.secKill = secKill;
    }

    @Override
    public String toString() {
        return "SuccessSecKill{" +
                "userPhone=" + userPhone +
                ", stat=" + stat +
                ", createTime=" + createTime +
                ", secKill=" + secKill +
                '}';
    }
}
