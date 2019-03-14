package com.example.eventssplitjoin.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@ToString
public class IncompleteLeadDiscovered implements Event {
    private final String leadId;
    private final Set<String> incompleteManeuverIds;
}
