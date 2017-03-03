package com.shuffle.dao;

import com.shuffle.entity.SuccessSecKill;
import org.apache.ibatis.annotations.Param;

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
    int insertSuccessSecKill(@Param("secKillID")long secKillID, @Param("userPhone")long userPhone);

    SuccessSecKill queryByIDWithSecKill(@Param("seckillID")long seckillID, @Param("userPhone")long userPhone);
}
