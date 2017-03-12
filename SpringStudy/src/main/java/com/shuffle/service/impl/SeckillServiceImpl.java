package com.shuffle.service.impl;

import com.shuffle.dao.SecKillDao;
import com.shuffle.dao.SuccessSecKillDao;
import com.shuffle.dao.cache.RedisDao;
import com.shuffle.dto.Exposer;
import com.shuffle.dto.SeckillExecution;
import com.shuffle.entity.SecKill;
import com.shuffle.entity.SuccessSecKill;
import com.shuffle.enums.SeckillStatEnum;
import com.shuffle.exception.RepeatKillException;
import com.shuffle.exception.SeckillCloseException;
import com.shuffle.exception.SeckillException;
import com.shuffle.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shawn.xu on 17/3/4.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillDao secKillDao;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SuccessSecKillDao successSecKillDao;

    private final static String salt = "1234123O&(#^@@fdjl;ajf1";

    public List<SecKill> getSeckillList() {
        return secKillDao.queryAll(0, 4);
    }

    public SecKill getById(long seckillID) {
        return secKillDao.queryByID(seckillID);
    }

    public Exposer exportSeckillUrl(long seckillID) {
//        SecKill secKill = secKillDao.queryByID(seckillID);
        SecKill secKill = redisDao.getSeckill(seckillID);
        if (secKill == null) {
            secKill = secKillDao.queryByID(seckillID);

            if (secKill == null) {
                return new Exposer(false, seckillID);
            } else {
                redisDao.putSeckill(secKill);
            }

        }



        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, nowTime.getTime(), startTime.getTime(),
                    endTime.getTime(), seckillID);
        }

        String md5 = getMD5(seckillID);
        return new Exposer(true, md5, seckillID);
    }


    @Transactional
    public SeckillExecution executeSeckill(long seckillID, long userPhone, String md5) throws RepeatKillException, SeckillCloseException, SeckillException {
        if (StringUtils.isEmpty(md5) || !md5.equals(getMD5(seckillID))) {
            throw new SeckillException("seckill data rewrite");
        }

        try {
            Date nowTime = new Date();
//            int updateCount = secKillDao.reduceNumber(seckillID, nowTime);
//            if (updateCount <= 0) {
//                throw new SeckillCloseException("seckill is closed");
//            } else {
//
//                int insertCount = successSecKillDao.insertSuccessSecKill(seckillID,userPhone);
//
//                if (insertCount <= 0) {
//                    throw new RepeatKillException("seckill repeated");
//                } else {
//                    SuccessSecKill successSecKill = successSecKillDao.queryByIDWithSecKill(seckillID, userPhone);
//
//                    return new SeckillExecution(seckillID, SeckillStatEnum.SUCCESS, successSecKill);
//                }
//
//            }

            // 优化
            int insertCount = successSecKillDao.insertSuccessSecKill(seckillID,userPhone);

            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                int updateCount = secKillDao.reduceNumber(seckillID, nowTime);
                if (updateCount <= 0) {
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    SuccessSecKill successSecKill = successSecKillDao.queryByIDWithSecKill(seckillID, userPhone);

                    return new SeckillExecution(seckillID, SeckillStatEnum.SUCCESS, successSecKill);
                }

            }

        } catch (SeckillCloseException  e1) {
            throw  e1;
        } catch (RepeatKillException e2) {
            throw  e2;
        } catch (Exception e) {
            logger.error("seckill inner error:", e);
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }

    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

        return md5;
    }

    public SeckillExecution executeSeckillByProcedure(long seckillId, long userPhone, String md5) {
        if (StringUtils.isEmpty(md5) || !md5.equals(getMD5(seckillId))) {
            return new SeckillExecution(seckillId,SeckillStatEnum.DATE_REWRITE);
        }
        Date killTime=new Date();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("seckillId",seckillId);
        map.put("phone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);

        try {
            secKillDao.killByProcedure(map);
            //get result
            int result= MapUtils.getInteger(map,"result",-2);
            if(result==1){
                SuccessSecKill successKilled=successSecKillDao.queryByIDWithSecKill(seckillId,userPhone);
                return new SeckillExecution(seckillId,SeckillStatEnum.SUCCESS,successKilled);
            }else{
                return new SeckillExecution(seckillId,SeckillStatEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,SeckillStatEnum.INNER_ERROR);
        }
    }
}
