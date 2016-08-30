package client;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Author:Shawn.Xu
 * Date:2016/8/30
 * Description:
 */
public class ClientToLog4j {
    //单客户端模拟
    public static void main(String[] args) {
        String propertiesPath = ClientToLog4j.class.getClassLoader().getResource("client.properties").getPath();
        if(propertiesPath.endsWith(".xml")) {
            DOMConfigurator.configure(propertiesPath);
        } else {
            PropertyConfigurator.configure(propertiesPath);
        }
        Logger log = Logger.getLogger(ClientToLog4j.class);

        for (int i=0; i<10; i++) {
            log.info("this is client log:" + i);
        }

    }

}
