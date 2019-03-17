package com.example.eventssplitjoin.domain.aggregates;

import com.example.eventssplitjoin.events.Event;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
public abstract class AggregateRoot {

    private final List<Event> changes = new LinkedList<>();

    @Getter
    public final String id;
    @Getter
    public final int version;

    public List<Event> getUncommittedChanges() {
        return changes;
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }

    public void loadsFromHistory(List<Event> history) {
        history.forEach(e -> applyChange(e, false));
    }

    protected void applyChange(Event event) {
        applyChange(event, true);
    }

    private void applyChange(Event event, boolean isNew) {
        // this.AsDynamic().Apply(@event);
        handleDynamic(event);
        if (isNew) {
            changes.add(event);
        }
    }

    private void handleDynamic(Event event) {
        final Class<Subscribe> annotation = Subscribe.class;
        final Class<? extends Event> eventClass = event.getClass();

        final Method handler = Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .filter(method -> method.getParameterCount() == 1)
                .filter(method -> method.getParameters()[0].getType() == eventClass)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(this.getClass().getSimpleName() + " does not have a method with the annotation '" + annotation + "' to handle '" + eventClass.getSimpleName() + "'"));

        try {
            handler.invoke(this, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot invoke method '" + handler + "' on '" + this.getClass().getSimpleName() + "' to handle '" + eventClass.getSimpleName() + "'", e);
        }
    }
}
