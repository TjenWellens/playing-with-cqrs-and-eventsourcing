package com.example.eventssplitjoin.infrastructure;

import com.example.eventssplitjoin.bus.CommandBus;
import com.example.eventssplitjoin.bus.EventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandBusRegisterBeanPostProcessor implements BeanPostProcessor {
    private final CommandBus bus;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        this.bus.register(bean);
        return bean;
    }
}
