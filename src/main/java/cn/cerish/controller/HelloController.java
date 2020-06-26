package cn.cerish.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    Logger logger=Logger.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
