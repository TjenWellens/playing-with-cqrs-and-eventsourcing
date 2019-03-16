package com.example.eventssplitjoin;

import com.example.eventssplitjoin.bus.CommandBus;
import com.example.eventssplitjoin.commands.LoadImages;
import com.example.eventssplitjoin.domain.ImageType;
import com.example.eventssplitjoin.bus.EventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final CommandBus bus;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner.run");
        bus.post(
                new LoadImages("project-1", new HashSet<>(Arrays.asList(
                ImageType.PANORAMIX,
                ImageType.HEATMAP
        ))));
    }
}

