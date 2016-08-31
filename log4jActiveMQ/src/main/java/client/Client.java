package client;

import org.apache.log4j.Logger;

public class Client  {
    private  static  Logger log = Logger.getLogger(Client.class);
    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            log.info("this is message:" + i);
        }
    }
}
