package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

@Slf4j
public class App {

    private static final String PROP_FILE_NAME = "config.properties";

    boolean interactiveMode = false;


    public static void main(String[] args) {
        App app = new App();
        app.setUp();
        app.run();
    }

    private void run() {
        if (interactiveMode) {
            runInteractiveMode();
        } else {
            quickRun();
        }
    }

    private void quickRun() {

        final EventManager eventManager = new EventManager();

        try {
            int asyncEventId = eventManager.createAsync(60);
            log.info("Async Event [{}] has been created.", asyncEventId);
            eventManager.start(asyncEventId);
            log.info("Async Event [{}] has been started.", asyncEventId);

            int syncEventId = eventManager.create(60);
            log.info("Sync Event [{}] has been created.", syncEventId);
            eventManager.start(syncEventId);
            log.info("Sync Event [{}] has been created.", syncEventId);

            eventManager.status(asyncEventId);
            eventManager.status(syncEventId);

            eventManager.cancel(asyncEventId);
            log.info("Async Event [{}] has been stopped.", asyncEventId);

            eventManager.cancel(syncEventId);
            log.info("Sync Event [{}] has been stopped.", syncEventId);
        } catch (MaxNumOfEventsAllowedException | LongRunningEventException | EventDoesNotExistException
                | InvalidOperationException e) {
            log.error(e.getMessage());
        }
    }

    private void runInteractiveMode() {

        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while (option != 4) {
            log.info("Hello. Would you like to boil some eggs?");
            log.info("(1) BOIL AN EGG \n(2) STOP BOILING THIS EGG \n(3) HOW ARE MY EGGS? \n(4) EXIT");
            log.info("Choose [1,2,3,4]: ");
            option = scanner.nextInt();
            if (option == 1) {
                processOption1(eventManager, scanner);
            } else if (option == 2) {
                processOption2(eventManager, scanner);
            } else if (option == 3) {
                processOption3(eventManager, scanner);
            } else if (option == 4) {
                eventManager.shutdown();
            }
        }
    }

    private void processOption3(EventManager eventManager, Scanner scanner) {
        scanner.next();
        log.info("Just one egg (O) OR all of them (A) ?: ");
        String nextLine = scanner.nextLine();
        if (nextLine.equalsIgnoreCase("0")) {
            log.info("Which egg?: ");
            int eventId = scanner.nextInt();
            try {
                eventManager.status(eventId);
            } catch (EventDoesNotExistException e) {
                log.error(e.getMessage());
            }
        } else if (nextLine.equalsIgnoreCase("A")) {
            eventManager.statusOfAllEvents();
        }
    }

    private void processOption2(EventManager eventManager, Scanner scanner) {
        log.info("Which egg?: ");
        var eventId = scanner.nextInt();
        try {
            eventManager.cancel(eventId);
            log.info("Egg [{}] is removed from boiler.", eventId);
        } catch (EventDoesNotExistException e) {
            log.error(e.getMessage());
        }
    }

    private void processOption1(EventManager eventManager, Scanner scanner) {
        scanner.nextLine();
        log.info("Boil multiple eggs at once (A) or boil them one-by-one (S)?: ");
        var eventType = scanner.nextLine();
        log.info("How long should this egg be boiled for (in seconds)?: ");
        var eventTime = scanner.nextInt();
        if (eventType.equalsIgnoreCase("A")) {
            try {
                var eventId = eventManager.createAsync(eventTime);
                eventManager.start(eventId);
                log.info("Egg [{}] is being boiled.", eventId);
            } catch (MaxNumOfEventsAllowedException | LongRunningEventException
                    | EventDoesNotExistException e) {
                log.error(e.getMessage());
            }
        } else if (eventType.equalsIgnoreCase("S")) {
            try {
                var eventId = eventManager.create(eventTime);
                eventManager.start(eventId);
                log.info("Egg [{}] is being boiled.", eventId);
            } catch (MaxNumOfEventsAllowedException | InvalidOperationException
                    | LongRunningEventException | EventDoesNotExistException e) {
                log.error(e.getMessage());
            }
        } else {
            log.info("Unknown event type.");
        }
    }

    public void setUp() {
        Properties properties = new Properties();
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                log.error("{} was not found. Defaulting to non-interactive mode", PROP_FILE_NAME, e);
            }
            String property = (String) properties.get("INTERACTIVE_MODE");
            if (property.equalsIgnoreCase("YES")) {
                interactiveMode = true;
            }
        }
    }
}
