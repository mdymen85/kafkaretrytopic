package com.kafka.retrytopic.consumer.aspects;

import com.kafka.retrytopic.config.retry.IsRetryTopicConsumerDisabled;
import com.kafka.retrytopic.consumer.EventConsumer;
import com.kafka.retrytopic.consumer.IControlKeyRepository;
import com.kafka.retrytopic.consumer.RedirectControlService;
import com.kafka.retrytopic.producer.IToProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
@IsRetryTopicConsumerDisabled
public class RedirectAspect {

    @Value("$(application.topic.to)")
    private String topic;

    private final RedirectControlService redirectControlService;
    private final IToProducer producer;

    @Around("execution(* com.kafka.retrytopic.consumer.Consumer.consumer(..)) && @annotation(com.kafka.retrytopic.consumer.aspects.Redirect)")
    public void redirect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        var eventConsumer = (EventConsumer) args[0];

        var key = eventConsumer.getNumber();

        //if has key in table, must redirect
        var hasKey = this.redirectControlService.hasKey(key);

        if (hasKey) {
            this.redirectControlService.add(key);
            this.producer.produce(eventConsumer);
            return;
        }
        proceedingJoinPoint.proceed(args);

    }

}
