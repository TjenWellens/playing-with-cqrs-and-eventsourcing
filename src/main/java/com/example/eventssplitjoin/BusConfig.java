package com.example.eventssplitjoin;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class BusConfig {
    @Bean
    EventBus eventBus() {
        return new AsyncEventBus(Executors.newCachedThreadPool());
    }
}
