package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class App {

    public  void run() {
        final var executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            final Word word = new Word();
            @Override
            public void run() {
                List<String> fetch = word.fetch();
                log.info("Our world currently has the following countries:-");
                fetch.stream().map(county -> "\t" + county).forEach(log::info);
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

}
