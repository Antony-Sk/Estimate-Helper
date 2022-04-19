package main.java.ru.innop.estatehelper.console;

import main.java.ru.innop.estatehelper.exceptions.RepeatUserLoginException;
import main.java.ru.innop.estatehelper.model.LoginForm;
import main.java.ru.innop.estatehelper.model.User;
import main.java.ru.innop.estatehelper.repositories.UserRepo;
import main.java.ru.innop.estatehelper.repositories.UserRepoImpl;
import main.java.ru.innop.estatehelper.service.LoginSignUpService;
import main.java.ru.innop.estatehelper.service.LoginSignUpServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputHandler {
    private final UserRepo userRepo = new UserRepoImpl();
    private final LoginSignUpService loginSignUpService = new LoginSignUpServiceImpl(userRepo);

    private String lastState;
    private String currentState;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start() throws IOException {
        currentState = lastState = "greeting";
        boolean working = true;
        String login;
        while (working) {
            System.out.print("\033[H\033[2J");
            switch (currentState) {
                case "greeting": {
                    System.out.println("Hello stranger!!");
                    System.out.println("It is the Estate Helper!!");
                    System.out.println("If you wanna exit type \"exit\", if you wanna login type \"login\", if you are here for the first time, please sigh up: (type \"signup\"): ");
                    lastState = currentState;
                    currentState = reader.readLine();
                    continue;
                }
                case "exit": {
                    System.out.println("Good bye!!");
                    working = false;
                    break;
                }
                case "login": {
                    System.out.println("If you wanna exit type \"exit\", if you wanna return back type \"greeting\", if you wanna continue type anything else");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit" : {
                            currentState = input;
                            continue;
                        }
                        case "greeting": {
                            currentState = lastState = "greeting";
                            continue;
                        }
                    }
                    System.out.print("Type your login: ");
                    login = reader.readLine();
                    System.out.print("Type your password: ");
                    String password = reader.readLine();
                    boolean isAuth = loginSignUpService.tryToLogin(login, password);
                    if (isAuth) {
                        System.out.println("Success. Hello " + login + "!");
                        lastState = currentState;
                        currentState = "menu";
                    } else {
                        System.out.println("Failure. Try again");
                    }
                    continue;
                }
                case "menu": {
                    System.out.println("If you wanna exit type \"exit\", if you wanna logout type \"greeting, if you wanna add new estate type ");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit" : {
                            currentState = input;
                            continue;
                        }
                        case "greeting": {
                            currentState = lastState = "greeting";
                            continue;
                        }
                    }

                }
                case "signup": {
                    System.out.println("If you wanna exit type \"exit\", if you wanna return back type \"greeting\", if you wanna continue type anything else");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit" : {
                            currentState = input;
                            continue;
                        }
                        case "greeting": {
                            currentState = lastState = "greeting";
                            continue;
                        }
                    }
                    System.out.print("Type your login: ");
                    login = reader.readLine();
                    System.out.print("Type your password: ");
                    String password = reader.readLine();
                    System.out.print("Type your e-mail: ");
                    String email = reader.readLine();
                    System.out.print("Type your contact number: ");
                    String number = reader.readLine();
                    try {
                        loginSignUpService.sighUpNewUser(new LoginForm(login, password, email, number));
                    } catch (RepeatUserLoginException e) {
                        System.out.println("Failure. Try again");
                        continue;
                    }
                    System.out.println("Success. Hello " + login + "!");
                    lastState = currentState;
                    currentState = "menu";
                }
            }
        }
    }
}
