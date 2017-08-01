package com.ilabs.log.registry;

import com.ilabs.log.formatters.SimpleFormatter;

/**
 * Created by Nilesh on 23-07-2017.
 */
public abstract class Handler {

    protected Formatter formatter;

    public Handler(){
        this.formatter = new SimpleFormatter();
    }

    public Handler(Formatter formatter){
        this.formatter = formatter;
    }

    public void setFormatter(Formatter formatter){
        this.formatter = formatter;
    }

    public abstract void init();

    public abstract void handle(final LogMessage message);

    public abstract void close();


}
