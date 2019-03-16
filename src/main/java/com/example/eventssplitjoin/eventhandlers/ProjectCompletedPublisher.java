package com.example.eventssplitjoin.eventhandlers;

import com.example.eventssplitjoin.events.IncompleteProjectDiscovered;
import com.example.eventssplitjoin.events.LeadCompleted;
import com.example.eventssplitjoin.events.ProjectCompleted;
import com.example.eventssplitjoin.bus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
class ProjectCompletedPublisher {
    private final EventBus bus;

    private HashMap<String, Set<String>> projectId_incompleteLeadIds = new HashMap<>();

    @Subscribe
    public void handle(IncompleteProjectDiscovered e) {
        projectId_incompleteLeadIds.put(e.getProjectName(), new HashSet<>(e.getLeadIds()));
    }

    @Subscribe
    public void handle(LeadCompleted e) {
        final Set<String> incompletedManeuverIds = projectId_incompleteLeadIds.get(e.getProjectId());
        final boolean removed = incompletedManeuverIds.remove(e.getLeadId());

        if (!removed)
            throw new IllegalStateException("No maneuver to remove: " + e);

        if (incompletedManeuverIds.isEmpty()) {
            bus.post(new ProjectCompleted(e.getProjectId()));
        }
    }
}
