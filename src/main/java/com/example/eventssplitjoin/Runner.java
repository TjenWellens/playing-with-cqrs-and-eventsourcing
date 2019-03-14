package com.example.eventssplitjoin;

import com.example.eventssplitjoin.commands.LoadImages;
import com.example.eventssplitjoin.domain.ImageType;
import com.example.eventssplitjoin.events.Event;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class Runner implements CommandLineRunner {
    private final EventBus eventBus;

    public Runner(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner.run");
        eventBus.post(new LoadImages("project-1", new HashSet<>(Arrays.asList(
                ImageType.PANORAMIX,
                ImageType.HEATMAP
        ))));
    }
}

@Component
class EventLogger {
    @Subscribe
    public void handle(Event e) {
        System.out.println(e.getClass().getSimpleName());
    }
}
