package com.spring.cloud.consumer.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {
    private Logger log = LoggerFactory.getLogger(BaseErrorController.class);

    @Override
    public String getErrorPath() {
        log.info("process occur error");
        return "error/error";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }

}

