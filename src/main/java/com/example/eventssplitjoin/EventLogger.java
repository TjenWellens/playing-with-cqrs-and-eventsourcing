package com.example.eventssplitjoin;

import com.example.eventssplitjoin.events.Event;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

@Component
class EventLogger {
    @Subscribe
    public void handle(Event e) {
        System.out.println(e.getClass().getSimpleName());
    }
}
