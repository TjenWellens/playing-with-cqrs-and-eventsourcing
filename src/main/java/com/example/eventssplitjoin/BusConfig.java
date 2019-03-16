package com.example.eventssplitjoin;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BusConfig {
    @Bean
    EventBus eventBus() {
//        final int maxValue = Integer.MAX_VALUE;
        final int maxValue = 10;
        return new AsyncEventBus(
                new ThreadPoolExecutor(0, maxValue,
                        60L, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>()));
    }
}
