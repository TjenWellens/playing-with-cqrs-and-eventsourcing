package com.example.eventssplitjoin;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusConfig {
    @Bean
    EventBus eventBus() {
        return new EventBus();
    }
}
