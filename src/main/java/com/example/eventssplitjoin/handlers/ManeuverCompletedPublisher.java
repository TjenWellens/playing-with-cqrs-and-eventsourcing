package com.example.eventssplitjoin.handlers;

import com.example.eventssplitjoin.domain.ImageType;
import com.example.eventssplitjoin.events.ImageCompleted;
import com.example.eventssplitjoin.events.IncompleteManeuverDiscovered;
import com.example.eventssplitjoin.events.ManeuverCompleted;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
@RequiredArgsConstructor
class ManeuverCompletedPublisher {
    private final EventBus bus;

    private HashMap<String, Set<ImageType>> maneuverId_imageTypes = new HashMap<>();

    @Subscribe
    public void handle(IncompleteManeuverDiscovered e) {
        maneuverId_imageTypes.put(e.getManeuverId(), e.getIncompleteImages());
    }

    @Subscribe
    public void handle(ImageCompleted e) {
        final Set<ImageType> incompletedImageTypes = maneuverId_imageTypes.get(e.getManeuverId());
        final boolean removed = incompletedImageTypes.remove(e.getImageType());

        if (!removed)
            throw new IllegalStateException("No maneuver to remove: " + e);

        if (incompletedImageTypes.isEmpty()) {
            bus.post(new ManeuverCompleted(e.getLeadId(), e.getManeuverId()));
        }
    }
}
