package com.example.springbootaoptodoexample.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))" )
    public Object logRequest(final ProceedingJoinPoint joinPoint) throws Throwable{

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes()))
                .getRequest();

        Object value;
        try {
          value = joinPoint.proceed();
        } finally {
            log.info("{} .{} . {} from {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getRemoteAddr(),
                    request.getHeader("user-id"));
        }



        return value;
    }

}
