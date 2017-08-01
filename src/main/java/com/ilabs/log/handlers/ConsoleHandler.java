package com.ilabs.log.handlers;

import com.ilabs.log.registry.Formatter;
import com.ilabs.log.registry.Handler;
import com.ilabs.log.registry.LogMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


/**
 * Created by Nilesh on 23-07-2017.
 */
public class ConsoleHandler extends Handler {

    private OutputStreamWriter stream;

    public ConsoleHandler(){
        super();
        //init();
    }

    public ConsoleHandler(Formatter formatter){
        super(formatter);
        //init();
    }

    @Override
    public void init() {
        this.stream = new OutputStreamWriter(System.err);
    }

    @Override
    public void handle(LogMessage message) {
        try {
            this.stream.write(this.formatter.format(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                this.stream.flush();
            }catch (IOException e){

            }
        }
    }

    @Override
    public void close() {
        try {
            this.stream.flush();
            this.stream.close();
        } catch (IOException e) {

        }finally {
            this.stream = null;
        }

    }
}
