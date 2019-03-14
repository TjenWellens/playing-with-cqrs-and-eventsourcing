package com.example.eventssplitjoin.repo;

import com.example.eventssplitjoin.domain.Maneuver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ManeuverRepo {
    public List<Maneuver> findManeuversThatAreIncomplete(String projectName) {
        final String leadstore1 = "leadstore-1";
        final String leadstore2 = "leadstore-2";
        final String leadstore3 = "leadstore-3";
        return Arrays.asList(
                new Maneuver("maneuver-1", leadstore1),
                new Maneuver("maneuver-2", leadstore2),
                new Maneuver("maneuver-3", leadstore2),
                new Maneuver("maneuver-4", leadstore3),
                new Maneuver("maneuver-5", leadstore3),
                new Maneuver("maneuver-6", leadstore3)
        );
    }
}
