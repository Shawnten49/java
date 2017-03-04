package com.shuffle.dto;

/**
 * Created by shawn.xu on 17/3/4.
 */
public class Exposer {
    private boolean exposed;

    private String md5;

    /**
     * 毫秒
     */
    private long now;

    private long start;

    private long end;

    private long seckillId;

    public Exposer(boolean exposed, String md5, long now, long start, long end, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.now = now;
        this.start = start;
        this.end = end;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long now, long start, long end, long seckillId) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, String md5, long seckillID) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillID;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
}
