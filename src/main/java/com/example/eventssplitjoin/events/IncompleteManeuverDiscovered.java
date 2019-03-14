package com.example.eventssplitjoin.events;

import com.example.eventssplitjoin.domain.ImageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@ToString
public class IncompleteManeuverDiscovered implements Event {
    private final String maneuverId;
    private final Set<ImageType> incompleteImages;
}
