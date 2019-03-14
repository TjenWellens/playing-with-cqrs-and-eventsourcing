package com.example.eventssplitjoin.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class LeadCompleted implements Event {
    private final String leadId;
}
