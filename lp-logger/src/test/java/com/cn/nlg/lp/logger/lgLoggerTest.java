package com.cn.nlg.lp.logger;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class lgLoggerTest {

    @Test
    public void testLog() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
