package com.example.eventssplitjoin;

import com.google.common.eventbus.EventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventBusRegisterBeanPostProcessor implements BeanPostProcessor {
    private final EventBus eventBus;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        this.eventBus.register(bean);
        return bean;
    }
}
