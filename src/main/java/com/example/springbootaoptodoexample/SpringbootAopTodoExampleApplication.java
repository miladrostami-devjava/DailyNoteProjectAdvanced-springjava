package com.example.springbootaoptodoexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAopTodoExampleApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootAopTodoExampleApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringbootAopTodoExampleApplication.class, args);

//        MDC.put("messageInfoMilad","This is  Customize message in Log for Info log - Author: miladRostami");
//        logger.info("This is info  log -Author: Milad.r",new IllegalAccessException("Please close  the Link!!!"));
//        logger.debug("This is Debug  log -Author: Milad.r");
     System.out.println("run ...");
//        MDC.remove("messageInfoMilad");
    }

}
