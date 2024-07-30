package com.example.springbootaoptodoexample.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface PerformanceMonitoring {
    String metricName() default "";
    Log_Level logLevel() default Log_Level.INFO;


    enum Log_Level {
        INFO,
        DEBUG,
        WARN,
        ERROR,
        TRACE,
        NORMAL,
        MIDDLE,
        SPEC
    }
}
