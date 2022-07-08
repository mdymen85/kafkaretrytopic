package com.kafka.retrytopic.consumer.aspects;

import com.kafka.retrytopic.config.retry.IsNotRetryTopicConsumer;
import com.kafka.retrytopic.consumer.EventConsumer;
import com.kafka.retrytopic.consumer.RedirectControlService;
import com.kafka.retrytopic.producer.IToProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
@IsNotRetryTopicConsumer
public class RedirectAspect {

    private final RedirectControlService redirectControlService;
    private final IToProducer producer;

    @Around("execution(* com.kafka.retrytopic.consumer.Consumer.consumer(..)) && @annotation(com.kafka.retrytopic.consumer.aspects.Redirect)")
    public void redirect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        var eventConsumer = (EventConsumer) args[0];

        var key = eventConsumer.getUuid();


        var existEvent = redirectControlService.existEvent(eventConsumer);
        if (existEvent) {
            producer.produce(eventConsumer);
            return;
        }

        //if has key in table, must redirect
        var hasKey = this.redirectControlService.hasKey(key);

        if (hasKey) {
            redirectControlService.doRedirect(eventConsumer);
            throw new Exception();
        }
        proceedingJoinPoint.proceed(args);

    }

}
