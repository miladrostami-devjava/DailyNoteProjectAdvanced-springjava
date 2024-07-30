package com.example.springbootaoptodoexample.aspects;


import com.example.springbootaoptodoexample.configuration.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class RestrictAdminAspect {

    @Before("@annotation(com.example.springbootaoptodoexample.aspects.Restrict) && execution(public * * (..))")
    public void restrictAdminBeforeLogin(final JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Restrict restrict = methodSignature.getMethod().getAnnotation(Restrict.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        if (restrict.admin() || !isAdmin(request)){
            throw new ForbiddenException("You Need to admin Access!");
        }
        if (restrict.localOnly()
        && !request.getRemoteAddr().equals("127.0.0.1")
        && !request.getRemoteAddr().equals("0:0:0:0:0:0:0:0:1")){
            throw new ForbiddenException("Only possible localhost!");
        }



    }

    private static boolean isAdmin(final HttpServletRequest http){
        String authorization = http.getHeader("Authorization");
        return authorization != null && authorization
                .replace("Bearer ","")
                .equalsIgnoreCase("admin");
    }

}
