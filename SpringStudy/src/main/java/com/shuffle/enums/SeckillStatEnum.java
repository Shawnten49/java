package com.shuffle.enums;

/**
 * Created by shawn.xu on 17/3/4.
 */
public enum SeckillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"内部错误");

    private int stat;

    private String statMsg;

    SeckillStatEnum(int stat, String statMsg) {
        this.stat = stat;
        this.statMsg = statMsg;
    }

    public int getStat() {
        return stat;
    }

    public String getStatMsg() {
        return statMsg;
    }

    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum seckillStatEnum : values()) {
            if (seckillStatEnum.getStat() == index) {
                return seckillStatEnum;
            }
        }

        return null;
    }
}
