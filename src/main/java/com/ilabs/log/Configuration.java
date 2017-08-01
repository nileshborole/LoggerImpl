package com.ilabs.log;

import java.util.Properties;

/**
 * Created by Nilesh on 23-07-2017.
 */
public class Configuration extends Properties {

    public Configuration(){
        super();
    }

    public Configuration(Properties properties){
        super(properties);
    }

    public String getHandler(String loggerName){
        return (String) this.get(loggerName+".handler");
    }

    public String getFormatter(String handlerName){
        return (String) this.get(handlerName+".formatter");
    }

    public String getPreDefinedLogLevel(String loggerName){
        return (String) this.get(loggerName+".level");
    }

}
