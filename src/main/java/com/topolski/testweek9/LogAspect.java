package com.topolski.testweek9;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    long actualTime;
    long newActualTime;

    @Before("execution(* com.topolski.testweek9..*(..))")
    private void beforeFileRead() {
        actualTime = System.currentTimeMillis();
    }

    @After("execution(* com.topolski.testweek9..*(..))")
    private void afterFileRead() {
        newActualTime = System.currentTimeMillis();
        System.out.println(newActualTime);
        System.out.println(newActualTime - actualTime);
    }
}
