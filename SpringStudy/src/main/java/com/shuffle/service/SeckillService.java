package com.shuffle.service;

import com.shuffle.dto.Exposer;
import com.shuffle.dto.SeckillExecution;
import com.shuffle.entity.SecKill;
import com.shuffle.exception.RepeatKillException;
import com.shuffle.exception.SeckillCloseException;
import com.shuffle.exception.SeckillException;

import java.util.List;

/**
 * Created by shawn.xu on 17/3/4.
 */
public interface SeckillService {

    List<SecKill> getSeckillList();

    SecKill getById(long seckillID);

    Exposer exportSeckillUrl(long seckillID);

    SeckillExecution executeSeckill(long seckillID, long userPhone, String md5)
            throws RepeatKillException, SeckillCloseException, SeckillException;


    SeckillExecution executeSeckillByProcedure(long seckillID, long userPhone, String md5);
}
