package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
    static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        log.trace("Hi trace");
//        log.atTrace().log("","","");
        log.debug("Hi debug");
        log.info("Hi info");
        log.warn("Hi warn");
        log.error("Hi error");
        log.info(log.getName());
    }
}

