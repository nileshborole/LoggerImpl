package com.ilabs.log.formatters;

import com.ilabs.log.registry.Formatter;
import com.ilabs.log.registry.LogMessage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nilesh on 23-07-2017.
 */
public class SimpleFormatter extends Formatter {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



    @Override
    public String format(LogMessage message) {
        StringBuilder builder = new StringBuilder();
        builder.append(dateFormat.format(new Date(message.getLogTime()))).append(" : ")
                .append(message.getClassName()).append(" [")
                .append(message.getLevel().getName()).append("] ")
                .append(message.getMessage()).append("\n");

        if(message.getThrowable() != null){
            StringWriter writer = new StringWriter();
            PrintWriter writer1 = new PrintWriter(writer);
            message.getThrowable().printStackTrace(writer1);
            builder.append(writer.toString());

        }

        builder.append("\n");
        return builder.toString();
    }
}
