package com.example.eventssplitjoin.events;

import com.example.eventssplitjoin.domain.ImageType;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;

@Getter
@ToString
public class IncompleteManeuverDiscovered implements Event {
    private final String maneuverId;
    private final Set<ImageType> incompleteImages;

    public IncompleteManeuverDiscovered(String maneuverId, Set<ImageType> incompleteImages) {
        this.maneuverId = maneuverId;
        this.incompleteImages = Collections.unmodifiableSet(incompleteImages);
    }
}
