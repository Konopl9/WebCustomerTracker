package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declaration
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forSDaoPackage() {

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forSDaoPackage()")
    private void forAppFlow() {

    }

    // add @Before advice
    @Before("forAppFlow()")
    public  void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("===>in @Before method called: " + method);

        Object[] args = joinPoint.getArgs();

        for(Object arg : args) {
            myLogger.info("argument: " + arg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("===>in @AfterReturning method called: " + method);
        // display data returned
        myLogger.info("===> result: " + result);
    }
}
