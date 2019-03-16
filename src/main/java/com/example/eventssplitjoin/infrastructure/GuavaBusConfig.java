package com.example.eventssplitjoin.infrastructure;

import com.example.eventssplitjoin.bus.CommandBus;
import com.example.eventssplitjoin.bus.EventBus;
import com.example.eventssplitjoin.commands.Command;
import com.example.eventssplitjoin.events.Event;
import com.google.common.eventbus.AsyncEventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class GuavaBusConfig {
    private com.google.common.eventbus.EventBus createBus() {
        final int maxValue = 10;
        final AsyncEventBus bus = new AsyncEventBus(
                new ThreadPoolExecutor(0, maxValue,
                        60L, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>()));
        return bus;
    }

    @Bean
    EventBus eventBus() {
        return new GuavaEventBusAdapter(createBus());
    }

    @Bean
    CommandBus commandBusBus() {
        return new GuavaCommandBusAdapter(createBus());
    }

    @RequiredArgsConstructor
    private static class GuavaEventBusAdapter implements EventBus {
        private final com.google.common.eventbus.EventBus bus;

        @Override
        public void post(Event event) {
            bus.post(event);
        }

        @Override
        public void register(Object object) {
            bus.register(object);
        }

        @Override
        public void unregister(Object object) {
            bus.unregister(object);
        }
    }

    @RequiredArgsConstructor
    private static class GuavaCommandBusAdapter implements CommandBus {
        private final com.google.common.eventbus.EventBus bus;

        @Override
        public void post(Command command) {
            bus.post(command);
        }

        @Override
        public void register(Object object) {
            bus.register(object);
        }

        @Override
        public void unregister(Object object) {
            bus.unregister(object);
        }
    }
}
