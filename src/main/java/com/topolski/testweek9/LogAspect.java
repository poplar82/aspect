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

    @Before("@annotation(CountAspect)")
    private void beforeFileRead() {
        actualTime = System.currentTimeMillis();
    }

    @After("@annotation(CountAspect)")
    private void afterFileRead() {
        newActualTime = System.currentTimeMillis();
        System.out.println(newActualTime - actualTime);
    }
}
