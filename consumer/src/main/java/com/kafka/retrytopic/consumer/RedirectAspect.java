package com.kafka.retrytopic.consumer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RedirectAspect {

    @Around("execution(* com.kafka.retrytopic.consumer.Consumer.consumer(..)) && @annotation(Redirect)")
    public void redirect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        var eventConsumer = (EventConsumer) args[0];

        proceedingJoinPoint.proceed(args);

    }

}
