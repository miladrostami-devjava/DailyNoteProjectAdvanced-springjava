package com.example.springbootaoptodoexample.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeLoggerAspect {

    @Around("@annotation(com.example.springbootaoptodoexample.aspects.Timed) && execution(public * *(..))")
    public Object time(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Long startTime = System.currentTimeMillis();
        Object value;
        try {
            value = proceedingJoinPoint.proceed();
        } finally {
            Long duration = System.currentTimeMillis() - startTime;
            log.info("{} . {} took {} ms",
                    proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(),
                    proceedingJoinPoint.getSignature().getName(),
                    duration);
        }
        return value;

    }
}
