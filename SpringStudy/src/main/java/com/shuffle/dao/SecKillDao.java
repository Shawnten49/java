package com.shuffle.dao;

import com.shuffle.entity.SecKill;

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
    int reduceNumber(long secKillID, Date killTime);

    List<SecKill> queryAll(int offset, int limit);

    SecKill queryByID(int secKillID);
}
