package org.kevin.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Task task = new SimpleTask();
        task.executeWith(()->log.info("I'm done now"));
    }
}
