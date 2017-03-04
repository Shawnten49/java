package com.shuffle.service;

import com.shuffle.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shawn.xu on 17/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<SecKill> list = seckillService.getSeckillList();
        logger.info("list={}", list);

    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void exportSeckillUrl() throws Exception {

    }

    @Test
    public void executeSeckill() throws Exception {

    }

}