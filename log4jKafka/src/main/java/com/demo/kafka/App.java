package com.demo.kafka;

import org.apache.log4j.Logger;

/**
 * Author:Shawn.Xu
 * Date:2016/8/31
 * Description:
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            LOGGER.info("Info [" + i + "]");
//            Thread.sleep(1000);
        }
    }
}
