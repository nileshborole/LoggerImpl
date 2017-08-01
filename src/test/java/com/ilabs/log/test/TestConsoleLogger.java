package com.ilabs.log.test;

import com.ilabs.log.Logger;
import com.ilabs.log.LoggerFactory;
import com.ilabs.log.registry.Level;
import org.junit.Test;

/**
 * Created by Nilesh on 23-07-2017.
 */
public class TestConsoleLogger {

    @Test
    public void testLogger(){
        Logger logger = LoggerFactory.getLoggerFactory().getLogger(TestConsoleLogger.class.getName(), Level.DEBUG);

        logger.all("test");

        logger.debug("test debug");
    }

}
