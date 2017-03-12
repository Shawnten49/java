package com.shuffle.dto;

import com.shuffle.entity.SuccessSecKill;
import com.shuffle.enums.SeckillStatEnum;

/**
 * Created by shawn.xu on 17/3/4.
 */
public class SeckillExecution {
    private long seckillId;

    private int state;

    private String stateInfo;

    private SuccessSecKill successSecKill;

    public SeckillExecution(long seckillId, SeckillStatEnum state) {
        this.seckillId = seckillId;
        this.state = state.getStat();
        this.stateInfo = state.getStatMsg();
    }

    public SeckillExecution(long seckillId, SeckillStatEnum state, SuccessSecKill successSecKill) {
        this.seckillId = seckillId;
        this.state = state.getStat();
        this.stateInfo = state.getStatMsg();
        this.successSecKill = successSecKill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSecKill getSuccessSecKill() {
        return successSecKill;
    }

    public void setSuccessSecKill(SuccessSecKill successSecKill) {
        this.successSecKill = successSecKill;
    }
}
