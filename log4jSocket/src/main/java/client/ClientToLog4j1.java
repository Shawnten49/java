package client;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Author:Shawn.Xu
 * Date:2016/8/30
 * Description:
 */
public class ClientToLog4j1 {

    private  int index;
    public ClientToLog4j1(int i) {
        this.index = i;
    }


    public void doTest() {
        String propertiesPath = ClientToLog4j1.class.getClassLoader().getResource("client" + index + ".properties").getPath();
        System.out.println("-----1---load:" + propertiesPath);
        if(propertiesPath.endsWith(".xml")) {
            DOMConfigurator.configure(propertiesPath);
        } else {
            PropertyConfigurator.configure(propertiesPath);
        }
        Logger log = Logger.getLogger(ClientToLog4j1.class);

        for (int i=0; i<10; i++) {
            log.info("this is client"+index+" log:" + i);
        }
    }
}
