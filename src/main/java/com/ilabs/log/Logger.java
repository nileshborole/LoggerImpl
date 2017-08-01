package com.ilabs.log;

import com.ilabs.log.handlers.ConsoleHandler;
import com.ilabs.log.registry.Handler;
import com.ilabs.log.registry.Level;
import com.ilabs.log.registry.LogLevel;
import com.ilabs.log.registry.LogMessage;
import sun.reflect.Reflection;

/**
 * Created by Nilesh on 23-07-2017.
 */
public class Logger {

    private Logger parent;
    private String name;
    private Handler handler;
    private LogLevel logLevel;

    Logger(String name){
        this(name, Level.ALL);
    }

    Logger(String name, LogLevel level){
        this.name = name;
        this.logLevel = level;
    }

    void setLogLevel(LogLevel level){
        this.logLevel = level;
    }

    void setHandler(Handler handler){
        this.handler = handler;
    }

    private void log(LogMessage message){
        if(message == null)
            return;

        if(this.logLevel.getLevel() > message.getLevel().getLevel())
            return;
        Class callerClazz = Reflection.getCallerClass();
        message.setClassName(callerClazz.getName());
        this.handler.handle(message);
    }

    public void log(LogLevel level, String msg, Throwable throwable){
        LogMessage message = new LogMessage(msg, throwable, level, name);
        log(message);
    }

    public void log(LogLevel level, String msg){
        log(level, msg, null);
    }

    public void log(LogLevel level, Throwable throwable){
        log(level, null, throwable);
    }

    public void all(String msg){
        all(msg, null);
    }

    public void all(String msg, Throwable throwable){
        if(this.logLevel.getLevel() > Level.ALL.getLevel())
            return;
        log(Level.ALL, msg, throwable);
    }

    public void all(Throwable throwable){
        log(null, throwable);
    }

    public void debug(String msg, Throwable throwable){
        if(this.logLevel.getLevel() > Level.DEBUG.getLevel())
            return;

        log(Level.DEBUG, msg, throwable);
    }

    public void debug(String msg){
        debug(msg, null);
    }

    public void debug(Throwable throwable){
        debug(null, throwable);
    }

    public void error(String msg, Throwable throwable){
        if(this.logLevel.getLevel() > Level.ERROR.getLevel())
            return;

        log(Level.ERROR, msg, throwable);
    }

    public void error(Throwable throwable){
        error(null, throwable);
    }

    public void error(String message){
        error(message, null);
    }

    public String getName(){
        return this.name;
    }

    public void setParent(Logger parent){
        this.parent = parent;
    }

}
