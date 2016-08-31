package client;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Author:Shawn.Xu
 * Date:2016/8/30
 * Description:
 */
public class Main {
    //多客户端模拟
    public static void main(String[] args) {
        String propertiesPath = ClientToLog4j2.class.getClassLoader().getResource("client1.properties").getPath();
        System.out.println("--------load:" + propertiesPath);
        if(propertiesPath.endsWith(".xml")) {
            DOMConfigurator.configure(propertiesPath);
        } else {
            PropertyConfigurator.configure(propertiesPath);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                ClientToLog4j1 clientToLog4j1 = new ClientToLog4j1(1);
                clientToLog4j1.doTest();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ClientToLog4j2 clientToLog4j2 = new ClientToLog4j2(2);
                clientToLog4j2.doTest();
            }
        }).start();
    }
}
