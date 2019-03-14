package com.example.eventssplitjoin.handlers;

import com.example.eventssplitjoin.events.IncompleteLeadDiscovered;
import com.example.eventssplitjoin.events.LeadCompleted;
import com.example.eventssplitjoin.events.ManeuverCompleted;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
@RequiredArgsConstructor
class LeadCompletedPublisher {
    private final EventBus bus;

    private HashMap<String, Set<String>> leadId_incompleteManeuverIds = new HashMap<>();

    @Subscribe
    public void handle(IncompleteLeadDiscovered e) {
        leadId_incompleteManeuverIds.put(e.getLeadId(), e.getIncompleteManeuverIds());
    }

    @Subscribe
    public void handle(ManeuverCompleted e) {
        final Set<String> incompletedManeuverIds = leadId_incompleteManeuverIds.get(e.getLeadId());
        final boolean removed = incompletedManeuverIds.remove(e.getManeuverId());

        if (!removed)
            throw new IllegalStateException("No maneuver to remove: " + e);

        if (incompletedManeuverIds.isEmpty()) {
            bus.post(new LeadCompleted(e.getLeadId(), e.getProjectId()));
        }
    }
}
