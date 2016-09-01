package clients;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Author:Shawn.Xu
 * Date:2016/8/30
 * Description:
 */
public class ClientToLog4j1 {
    //单客户端模拟
    public static void main(String[] args) {
        new ClientToLog4j1().testLog();
    }

    public void testLog() {
        String propertiesPath = ClientToLog4j1.class.getClassLoader().getResource("client1.properties").getPath();
        System.out.println("load propertiesPath:" + propertiesPath);
        if(propertiesPath.endsWith(".xml")) {
            DOMConfigurator.configure(propertiesPath);
        } else {
            PropertyConfigurator.configure(propertiesPath);
        }
        Logger log = Logger.getLogger(ClientToLog4j1.class);

        //开始循环写日志
        int count = 0;
        while (true) {
          /* try {
               Thread.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }*/
            log.info("this is ===client1 :: count:" + count + ";" + System.currentTimeMillis());

            count++;
            if (count>10000) {
                break;
            }
        }
    }

}
