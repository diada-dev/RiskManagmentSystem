package RMS;

import org.apache.log4j.*;

public class SyslogServer {

    private static final Logger logger = LogManager.getLogger(SyslogServer.class);
    
    /*example
         logger.debug("debug message");
         logger.info("info message");
         logger.warn("warn message");
         logger.error("error message");
         try {
             int i = 1/0;
         }catch(Exception exc) {
             logger.error("error message with stack trace",
                     new Exception("I forced this exception",exc));
         }
         logger.fatal("fatal message");
    */


    public static void main(String[] args) throws Exception {} 

    public static void info(String mess){logger.info(mess);}

    public static void warn(String mess){logger.warn(mess);}

}