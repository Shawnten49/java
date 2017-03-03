package com.shuffle.dao;

import com.shuffle.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配置spring和junit整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {

    @Resource
    private SecKillDao secKillDao;

    @Test
    public void testReduceNumber() throws Exception {
            int num = secKillDao.reduceNumber(1000L, new Date());
        System.out.println(num);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<SecKill> list = secKillDao.queryAll(0, 100);

        for (SecKill secKill : list) {
            System.out.println(secKill);
        }
    }

    @Test
    public void testQueryByID() throws Exception {
        long id = 1000;
        SecKill secKill = secKillDao.queryByID(id);

        System.out.println(secKill.getName());
    }
}