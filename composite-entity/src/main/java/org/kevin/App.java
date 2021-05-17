package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class App {

    public static void main(String[] args) {
        new App("No Danger", "Green Light");
    }

    public App(String message,String signal) {
        CompositeEntity console = new CompositeEntity();
        console.init();
        console.setData(message,signal);
        Arrays.stream(console.getData()).forEach(log::info);
        console.setData("Danger","Red Light");
        Arrays.stream(console.getData()).forEach(log::info);
    }
}
