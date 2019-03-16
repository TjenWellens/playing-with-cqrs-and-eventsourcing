package com.example.eventssplitjoin.handlers;

import com.example.eventssplitjoin.events.ImageCompleted;
import com.example.eventssplitjoin.events.IncompleteImageDiscovered;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ImageCompletedPublisher {
    private final EventBus bus;

    @Subscribe
    public void handle(IncompleteImageDiscovered e) {
        fetchImages();

        bus.post(new ImageCompleted(e.getProjectId(), e.getLeadId(), e.getManeuverId(), e.getImageType()));
    }

    private void fetchImages() {
        // todo: fetch image, store image, store url
        try {
            Thread.sleep((5000));
        } catch (InterruptedException e1) {
        }
    }
}
