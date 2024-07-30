package com.example.springbootaoptodoexample.aspects;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final  Logger log = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

@Autowired
    private  MeterRegistry meterRegistry;


@Around("@annotation(monitoring)")
    public Object monitorPerformance(final ProceedingJoinPoint joinPoint, PerformanceMonitoring monitoring) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return         joinPoint.proceed();
        } finally {
            long durationTime = System.currentTimeMillis() - startTime;
            logPerformance(monitoring.logLevel(),monitoring.metricName(),joinPoint,durationTime);
            sendMetricsToMonitoringSystem(monitoring.metricName(),durationTime);
        }




    }

    private void logPerformance(PerformanceMonitoring.Log_Level logLevel,
                                String metricName,
                                ProceedingJoinPoint joinPoint,
                                Long durationTime) {
        String message = String.format("MetricName %s Method %s executed in %d ms ",
                metricName,
                joinPoint.getSignature().toShortString(),
                durationTime);
        switch (logLevel) {
            case INFO -> log.info(message);
            case WARN -> log.warn(message);
            case DEBUG -> log.debug(message);
            case ERROR -> log.error(message);
            case TRACE -> log.trace(message);
            case SPEC -> log.atWarn().log(message);
            case MIDDLE -> log.isInfoEnabled();
            case NORMAL -> log.info(message.toLowerCase());
        }

    }

    private void sendMetricsToMonitoringSystem(String metricName, Long durationTime) {
        Timer timer = meterRegistry.timer(metricName);
        timer.record(durationTime, TimeUnit.MILLISECONDS);
    }


}
