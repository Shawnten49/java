package com.shuffle.dao;

import com.shuffle.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Author:Shawn.Xu
 * Date:2017/3/2
 * Description:
 */
public interface SecKillDao {

    /**
     *
     * @param secKillID
     * @param killTime
     * @return 更新影响的行数
     */
    int reduceNumber(@Param("secKillID")long secKillID, @Param("killTime")Date killTime);

    List<SecKill> queryAll(@Param("offset")int offset, @Param("limit")int limit);

    SecKill queryByID(long secKillID);
}

