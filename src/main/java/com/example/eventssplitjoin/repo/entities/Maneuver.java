package com.example.eventssplitjoin.repo.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Maneuver {
    private final String id;
    private final String leadstoreId;
    private final String projectId;
}
