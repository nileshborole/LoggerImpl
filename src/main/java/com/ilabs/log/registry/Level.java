package com.ilabs.log.registry;

/**
 * Created by Nilesh on 23-07-2017.
 */
public enum Level implements LogLevel {

    ALL("ALL", 0),
    DEBUG("DEBUG",1),
    ERROR("ERROR", 2),
    OFF("OFF", Integer.MAX_VALUE);

    private final String name;
    private final int level;

    private Level(String name, int level){
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
