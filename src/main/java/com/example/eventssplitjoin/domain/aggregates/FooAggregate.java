package com.example.eventssplitjoin.domain.aggregates;

import com.example.eventssplitjoin.events.Event;
import com.example.eventssplitjoin.events.ImageCompleted;
import com.example.eventssplitjoin.events.IncompleteImageDiscovered;
import com.google.common.eventbus.Subscribe;

public class FooAggregate extends AggregateRoot {
    public FooAggregate(String id, int version) {
        super(id, version);
    }

    @Subscribe
    public void handle(ImageCompleted e) {
        System.out.println("Handle ImageCompleted: " + e);
    }

    @Subscribe
    public void handle(IncompleteImageDiscovered e) {
        System.out.println("Handle IncompleteImageDiscovered: " + e);
    }

}
