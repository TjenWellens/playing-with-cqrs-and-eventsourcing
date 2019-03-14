package com.example.eventssplitjoin.handlers;

import com.example.eventssplitjoin.commands.LoadImages;
import com.example.eventssplitjoin.domain.Maneuver;
import com.example.eventssplitjoin.domain.Project;
import com.example.eventssplitjoin.events.Event;
import com.example.eventssplitjoin.events.IncompleteImageDiscovered;
import com.example.eventssplitjoin.events.IncompleteLeadDiscovered;
import com.example.eventssplitjoin.events.IncompleteManeuverDiscovered;
import com.example.eventssplitjoin.repo.ManeuverRepo;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
class LoadImagesHandler {
    private final EventBus bus;
    private final ManeuverRepo maneuverRepo;

    @Subscribe
    public void handle(LoadImages e) {
        // create events: IncompleteLeadDiscovered, IncompleteManeuverDiscovered, IncompleteImageDiscovered

        final List<Maneuver> maneuvers = maneuverRepo.findManeuversThatAreIncomplete(e.getProjectName());
        Iterable<LoadImagesHandler.Lead> iterable = () -> new LoadImagesHandler.ManeuversIterator(maneuvers);
        StreamSupport.stream(iterable.spliterator(), false)
                .flatMap(lead -> {
                    Collection<Event> events = new LinkedList<>();

                    final Set<String> maneuverIds = lead.getManeuvers().stream().map(Maneuver::getId).collect(Collectors.toSet());
                    events.add(new IncompleteLeadDiscovered(lead.getLeadstoreId(), maneuverIds));

                    lead.getManeuvers().forEach(maneuver -> {
                        events.add(new IncompleteManeuverDiscovered(maneuver.getId(), e.getImageTypes()));

                        e.getImageTypes().forEach(imageType -> {
                            events.add(new IncompleteImageDiscovered(lead.getLeadstoreId(), maneuver.getId(), imageType));
                        });
                    });

                    return events.stream();
                })
                .forEach(bus::post);
    }

    private Project createProject(String name) {
        return new Project("project-1", name);
    }

    private static class ManeuversIterator implements Iterator<LoadImagesHandler.Lead> {
        private final Queue<Maneuver> maneuvers;

        private ManeuversIterator(List<Maneuver> maneuvers) {
            this.maneuvers = new LinkedList<>(maneuvers);
        }

        @Override
        public boolean hasNext() {
            return this.maneuvers.peek() != null;
        }

        @Override
        public LoadImagesHandler.Lead next() {
            List<Maneuver> leadManeuvers = new LinkedList<>();

            if (this.maneuvers.peek() == null)
                return null;

            String leadId = this.maneuvers.peek().getLeadstoreId();

            do {
                leadManeuvers.add(this.maneuvers.poll());
            } while (this.maneuvers.peek() != null && leadId.equals(this.maneuvers.peek().getLeadstoreId()));

            return new LoadImagesHandler.Lead(leadId, leadManeuvers);
        }
    }

    @Getter
    @RequiredArgsConstructor
    private static class Lead {
        private final String leadstoreId;
        private final List<Maneuver> maneuvers;
    }

}

