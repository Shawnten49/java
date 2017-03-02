package com.shuffle.dao;

import com.shuffle.entity.SuccessSecKill;

/**
 * Author:Shawn.Xu
 * Date:2017/3/2
 * Description:
 */
public interface SuccessSecKillDao {

    /**
     *
     * @param secKillID
     * @param userPhone
     * @return 插入返回的行数
     */
    int insertSuccessSecKill(long secKillID, long userPhone);

    SuccessSecKill queryByIDWithSecKill(long seckillID, long userPhone);
}
