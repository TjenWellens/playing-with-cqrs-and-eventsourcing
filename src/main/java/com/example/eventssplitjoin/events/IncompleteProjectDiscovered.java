package com.example.eventssplitjoin.events;

import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;

@Getter
@ToString
public class IncompleteProjectDiscovered implements Event {
    private final String projectName;
    private final Set<String> leadIds;

    public IncompleteProjectDiscovered(String projectName, Set<String> leadIds) {

        this.projectName = projectName;
        this.leadIds = Collections.unmodifiableSet(leadIds);
    }
}
