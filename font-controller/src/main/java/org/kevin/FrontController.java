package org.kevin;

import java.lang.reflect.InvocationTargetException;

public class FrontController {

    public void handleRequest(String request){
        Command command = getCommand(request);
        command.process();
    }

    private Command getCommand(String request) {
        Class<?> commandClass = getCommandClass(request);
        try {
            return (Command) commandClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ApplicationException(e);
        }
    }

    private static Class<?> getCommandClass(String request) {
        try {
            return Class.forName("org.kevin." + request + "Command");
        } catch (ClassNotFoundException e) {
            return UnknownCommand.class;
        }
    }
}
