package com.example.eventssplitjoin.commands;

import com.example.eventssplitjoin.domain.Maneuver;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class ImportManeuvers implements Command {
    private final String projectName;
    private final List<Maneuver> maneuvers;

    public ImportManeuvers(String projectName, List<Maneuver> maneuvers) {
        this.projectName = projectName;
        this.maneuvers = Collections.unmodifiableList(maneuvers);
    }
}
