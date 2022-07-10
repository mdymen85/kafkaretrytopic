package com.kafka.retrytopic.consumer.aspects;

import com.kafka.retrytopic.config.retry.IsRetryTopicConsumer;
import com.kafka.retrytopic.consumer.EventConsumer;
import com.kafka.retrytopic.consumer.SubstractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
@IsRetryTopicConsumer
public class SubstractAspect {

    private final SubstractService substractService;

    @After("execution(* com.kafka.retrytopic.consumer.Consumer.consumer(..)) && @annotation(com.kafka.retrytopic.consumer.aspects.Substract)")
    public void substract(JoinPoint joinPoint)  {
        Object[] args = joinPoint.getArgs();
        var eventConsumer = (EventConsumer) args[0];
        substractService.substract(eventConsumer);
    }
}
