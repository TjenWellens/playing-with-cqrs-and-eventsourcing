package com.example.eventssplitjoin.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ManeuverCompleted implements Event {
    private final String projectId;
    private final String leadId;
    private final String maneuverId;
}
