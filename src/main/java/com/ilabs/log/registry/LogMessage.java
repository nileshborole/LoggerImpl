package com.ilabs.log.registry;




/**
 * Created by Nilesh on 23-07-2017.
 */
public final class LogMessage {

    private String message;
    private final LogLevel level;
    private Throwable throwable;
    private final long logTime;
    private final String logName;
    private String className;

    LogMessage(LogLevel level, String logName){
        this.logTime = System.currentTimeMillis();
        this.level = level;
        this.logName = logName;
    }

    public LogMessage(String message, LogLevel level, String logName){
        this(level, logName);
        this.message = message;
    }

    public LogMessage(Throwable throwable, LogLevel level, String logName){
        this(level, logName);
        this.throwable = throwable;
    }

    public LogMessage(String message, Throwable throwable, LogLevel level, String logName){
        this(level, logName);
        this.message = message;
        this.throwable = throwable;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public long getLogTime() {
        return logTime;
    }

    public String getLogName() {
        return logName;
    }


}
