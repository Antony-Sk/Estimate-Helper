package main.java.ru.innop.estatehelper.app;

import main.java.ru.innop.estatehelper.console.ConsoleInputHandler;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException { // entrypoint
        (new ConsoleInputHandler()).start();
    }
}

