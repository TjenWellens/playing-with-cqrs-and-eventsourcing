package com.example.eventssplitjoin.commands;

import com.example.eventssplitjoin.domain.ImageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class LoadImages implements Command {
    private final String projectName;
    private final Set<ImageType> imageTypes;
}
