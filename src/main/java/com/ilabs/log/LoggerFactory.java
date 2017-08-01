package com.ilabs.log;

import com.ilabs.log.handlers.ConsoleHandler;
import com.ilabs.log.registry.Formatter;
import com.ilabs.log.registry.Handler;
import com.ilabs.log.registry.Level;
import com.ilabs.log.registry.LogLevel;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Nilesh on 23-07-2017.
 */
public final class LoggerFactory {

    private static LoggerFactory factory;
    private final Map<String, Logger> loggerMap;
    private Handler defaultHandler;
    private Configuration loggerConfiguration;
    private static Logger ROOT_LOGGER = new Logger("ROOT", Level.ALL);

    private volatile static boolean isInitialized = false;

    private LoggerFactory(){
        loggerMap =new ConcurrentHashMap<String, Logger>();
        init();
    }

    private void init(){
        defaultHandler = new ConsoleHandler();
        defaultHandler.init();
    }

    public static LoggerFactory getLoggerFactory(final Configuration configuration){

        if(factory == null){

                    factory = new LoggerFactory();
                    factory.setLoggerConfiguration(configuration);


        }
        isInitialized = true;
        return factory;
    }

    public static LoggerFactory getLoggerFactory(){
        return getLoggerFactory(loadLoggerConfiguration());
    }

    public static Logger getLogger(String name){
        return getLogger(name, Level.ALL);
    }

    public static Logger getLogger(String name, LogLevel level){
        LoggerFactory factory1 = getLoggerFactory();
        Logger logger = factory1.loggerMap.get(name);
        if(logger == null){
            try {
                logger = factory1.getLoggerInstance(name, level);

            }catch (Exception e){
                throw new Error(e);
            }
        }
        return logger;
    }


    public void registerLogger(Logger logger){

        String name = logger.getName();
        loggerMap.put(name, logger);

        int startIndex = 1;
        Logger root = ROOT_LOGGER;
        while( (startIndex = name.indexOf(".", startIndex)) > 0){
            root = getLogger(name.substring(0,  startIndex));
            startIndex = startIndex+1;
        }
        logger.setParent(root);
    }

    private Logger getLoggerInstance(String name, LogLevel level) throws Exception{

        Logger logger = new Logger(name, level);
        logger.setHandler(defaultHandler);
        String handlerClass = this.loggerConfiguration.getHandler(name);
        String formatterClass = this.loggerConfiguration.getFormatter(handlerClass);

        if(handlerClass != null && !handlerClass.trim().isEmpty()){
            Formatter formatter = null;
            if(formatterClass != null && !formatterClass.trim().isEmpty()){
                formatter = (Formatter)Class.forName(formatterClass.trim()).newInstance();
            }
            Handler handler = (Handler) Class.forName(handlerClass.trim()).newInstance();
            handler.init();
            handler.setFormatter(formatter);
            logger.setHandler(handler);
        }

        String logLevel = this.loggerConfiguration.getPreDefinedLogLevel(name);
        if(logLevel != null && !logLevel.trim().isEmpty()){
            logger.setLogLevel(Level.valueOf(logLevel));
        }

        registerLogger(logger);

        return logger;
    }

    private void setLoggerConfiguration(Configuration configuration){
         this.loggerConfiguration = configuration;
    }

    private static Configuration loadLoggerConfiguration(){
        // load from property file.
        // TODO
        return new Configuration();

    }


}
