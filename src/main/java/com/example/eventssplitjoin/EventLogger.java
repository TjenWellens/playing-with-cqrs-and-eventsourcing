package com.example.eventssplitjoin;

import com.example.eventssplitjoin.events.Event;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
class EventLogger {
    @Subscribe
    public void handle(Event e) {
        final String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        final String tag = e.getClass().getSimpleName();
        final String message = e.toString();
        System.out.println(date + "  " + tag + " --- " + message);
    }
}
