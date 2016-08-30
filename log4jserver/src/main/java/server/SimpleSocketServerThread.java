package server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * log4j服务器类，相当于SimpleSocketServer类
 */
public class SimpleSocketServerThread {
    private static Logger logger =  Logger.getLogger(SimpleSocketServerThread.class);
    public  int port;
    public  String configFile;
    ServerSocket serverSocket = null;
    static int count = 0;

    Socket socket;
    LoggerRepository hierarchy = LogManager.getLoggerRepository();
    ObjectInputStream ois;

    public SimpleSocketServerThread(){
    }

    public SimpleSocketServerThread(int port,String configFile){
        this.port = port;
        this.configFile = configFile;
        if(configFile.endsWith(".xml")) {
            DOMConfigurator.configure(configFile);
        } else {
            PropertyConfigurator.configure(configFile);
        }

        try {
            serverSocket = new ServerSocket(port);
            while(true){
                socket = serverSocket.accept();
                ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                new ServerToLog4j(hierarchy,ois,socket);
            }
        } catch (IOException e) {
            logger.error(" ServerSocket IOException");
        }
    }

    public static void main(String[] args) {
        String propertiesPath = SimpleSocketServerThread.class.getClassLoader().getResource("server.properties").getPath();
//        System.out.println(propertiesPath);
        SimpleSocketServerThread st = new SimpleSocketServerThread(9001,propertiesPath);
    }
}

/**
 * 起一个线程监听客户端发过来的消息，并写进log4j ；本类改写了SocketNode类的源代码
 */
class ServerToLog4j extends Thread{
    private static Logger logger =  Logger.getLogger(ServerToLog4j.class);
    private LoggerRepository hierarchy;
    ObjectInputStream ois;
    Socket socket;
    public ServerToLog4j(){

    }

    public ServerToLog4j(LoggerRepository hierarchy,ObjectInputStream ois,Socket socket){
        this.hierarchy = hierarchy;
        this.ois = ois;
        this.socket = socket;
        start();
    }

    public void run(){
        LoggingEvent event;
        Logger remoteLogger;

        try {
            if (ois != null) {
                while(true) {
                    // read an event from the wire
                    event = (LoggingEvent) ois.readObject();
                    // get a logger from the hierarchy. The name of the logger is taken to be the name contained in the event.
                    remoteLogger = hierarchy.getLogger(event.getLoggerName());
                    //event.logger = remoteLogger;
                    // apply the logger-level filter
                    if(event.getLevel().isGreaterOrEqual(remoteLogger.getEffectiveLevel())) {
                        // finally log the event as if was generated locally
                        remoteLogger.callAppenders(event);
                    }
                }
            }
        } catch(java.io.EOFException e) {
            //读取的时候到达尾端抛出的异常，屏蔽掉
            //logger.error("Caught java.io.EOFException closing conneciton.");
        } catch(java.net.SocketException e) {
//            logger.error("Caught java.net.SocketException closing conneciton.");
        } catch(InterruptedIOException e) {
            Thread.currentThread().interrupt();
            logger.error("Caught java.io.InterruptedIOException: ");
            logger.error("Closing connection.");
        } catch(IOException e) {
            logger.error("Caught java.io.IOException: ");
            logger.error("Closing connection.");
        } catch(Exception e) {
            logger.error("Unexpected exception. Closing conneciton.");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch(Exception e) {
                    logger.error("ObjectInputStream Could not close connection.");
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch(InterruptedIOException e) {
                    Thread.currentThread().interrupt();
                } catch(IOException ex) {
                }
            }
        }
    }
}


