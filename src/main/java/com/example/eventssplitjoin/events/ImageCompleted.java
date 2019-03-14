package com.example.eventssplitjoin.events;

import com.example.eventssplitjoin.domain.ImageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ImageCompleted implements Event {
    private final String leadId;
    private final String maneuverId;
    private final ImageType imageType;
}
