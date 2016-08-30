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
        String propertiesPath = ClientToLog4j1.class.getClassLoader().getResource("client1.properties").getPath();
        if(propertiesPath.endsWith(".xml")) {
            DOMConfigurator.configure(propertiesPath);
        } else {
            PropertyConfigurator.configure(propertiesPath);
        }
        Logger log = Logger.getLogger(ClientToLog4j1.class);

        int count = 0;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
           try {
               Thread.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           log.info("this is ===client1 :: count:" + count + ";" + System.currentTimeMillis());

           count++;
           if (count>10000) {
               break;
           }
       }

    }

}
