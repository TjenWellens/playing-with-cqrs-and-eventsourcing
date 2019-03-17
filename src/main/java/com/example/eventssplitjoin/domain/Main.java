package com.example.eventssplitjoin.domain;

import com.example.eventssplitjoin.domain.aggregates.AggregateRoot;
import com.example.eventssplitjoin.domain.aggregates.FooAggregate;
import com.example.eventssplitjoin.events.ImageCompleted;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AggregateRoot aggregate = new FooAggregate("id", 10);
        aggregate.loadsFromHistory(Arrays.asList(
                new ImageCompleted("", "", "",  null)
        ));
    }
}
