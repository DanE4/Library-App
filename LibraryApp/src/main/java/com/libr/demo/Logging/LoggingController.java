package com.libr.demo.Logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoggingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}