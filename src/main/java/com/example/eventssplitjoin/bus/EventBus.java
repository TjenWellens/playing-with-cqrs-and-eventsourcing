package com.example.eventssplitjoin.bus;

import com.example.eventssplitjoin.events.Event;

public interface EventBus {
    void post(Event event);

    void register(Object object);

    void unregister(Object object);
}
