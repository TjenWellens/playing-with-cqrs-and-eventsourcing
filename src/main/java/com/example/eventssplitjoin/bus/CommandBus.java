package com.example.eventssplitjoin.bus;

import com.example.eventssplitjoin.commands.Command;

public interface CommandBus {
    void post(Command event);

    void register(Object object);

    void unregister(Object object);
}
