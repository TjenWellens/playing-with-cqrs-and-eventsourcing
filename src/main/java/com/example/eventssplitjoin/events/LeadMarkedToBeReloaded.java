package com.example.eventssplitjoin.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
@ToString
public class LeadMarkedToBeReloaded implements Event {
    private final String leadstoreId;
    private final Collection<String> maneuverIds;
}
