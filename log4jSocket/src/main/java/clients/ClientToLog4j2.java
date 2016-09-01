package clients;

import org.apache.log4j.*;
import org.apache.log4j.net.SocketAppender;

/**
 * Author:Shawn.Xu
 * Date:2016/8/30
 * Description:
 */
public class ClientToLog4j2 {
    //单客户端模拟
    public static void main(String[] args) {
        new ClientToLog4j2().testLog();
    }

    public void testLog() {

        //配置文件加载
     /*  String propertiesPath = null;
       try {
           propertiesPath  = ClientToLog4j2.class.getClassLoader().getResource("client2.properties").getPath();
       } catch (Exception e) {
           propertiesPath = "F:\\shuffle\\github\\java2\\trunk\\log4jSocket\\target\\classes\\client2.properties";
           e.printStackTrace();
       }
        System.out.println("load propertiesPath:" + propertiesPath);
        if(propertiesPath.endsWith(".xml")) {
            DOMConfigurator.configure(propertiesPath);
        } else {
            PropertyConfigurator.configure(propertiesPath);
        }*/


      /*  pro.put("log4j.appender.C", "org.apache.log4j.ConsoleAppender");
        pro.put("log4j.appender.C.Threshold", "INFO");
        pro.put("log4j.appender.C.layout", "org.apache.log4j.PatternLayout");
        pro.put("log4j.appender.C.layout.ConversionPattern", "%n %m");
        PropertyConfigurator.configure(pro);
        */

        //代码加载logger
        Logger log=Logger.getLogger("test");
        log.setLevel(Level.INFO);
//        log.addAppender(new ConsoleAppender(new SimpleLayout()));

        SocketAppender socketAppender = new SocketAppender();
        socketAppender.setRemoteHost("127.0.0.1");
        socketAppender.setPort(9001);
        socketAppender.setLocationInfo(true);
        socketAppender.setReconnectionDelay(1000);
        PatternLayout layout1=new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss}  %m%n");
        socketAppender.setLayout(layout1);
//        socketAppender.setAppend(true);
        socketAppender.activateOptions();
        log.addAppender(socketAppender);

        RollingFileAppender rollingFileAppender = new RollingFileAppender();
        rollingFileAppender.setFile("F:\\log4j\\log4jserver\\client\\log2.log");
        rollingFileAppender.setMaxFileSize("1024KB");
        rollingFileAppender.setMaxBackupIndex(999);
        rollingFileAppender.setLayout(new SimpleLayout());
        rollingFileAppender.setName("rollingFile");
        PatternLayout layout=new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss}  %m%n");
        rollingFileAppender.setLayout(layout);
        rollingFileAppender.setAppend(true);
        rollingFileAppender.activateOptions();
        log.addAppender(rollingFileAppender);


//        Logger log = Logger.getLogger(ClientToLog4j2.class);
        int count = 0;
        while (true) {
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            log.info("this is ***client2 :: count:" + count + ";" + System.currentTimeMillis());

            count++;
            if (count>10000) {
                break;
            }
        }
    }

}
