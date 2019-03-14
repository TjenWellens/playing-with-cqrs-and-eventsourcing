package com.example.eventssplitjoin.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ProjectCreated implements Event {
    private final String id;
    private final String projectName;
}
