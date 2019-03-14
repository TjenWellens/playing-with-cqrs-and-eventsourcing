package com.example.eventssplitjoin.handlers;

import com.example.eventssplitjoin.domain.ImageType;
import com.example.eventssplitjoin.events.ImageCompleted;
import com.example.eventssplitjoin.events.IncompleteImageDiscovered;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
@RequiredArgsConstructor
class ImageCompletedPublisher {
    private final EventBus bus;

    private HashMap<String, Set<ImageType>> maneuverId_imageTypes = new HashMap<>();

    @Subscribe
    public void handle(IncompleteImageDiscovered e) {
        // todo: fetch image, store image, store url
        bus.post(new ImageCompleted(e.getProjectId(), e.getLeadId(), e.getManeuverId(), e.getImageType()));
    }
}
