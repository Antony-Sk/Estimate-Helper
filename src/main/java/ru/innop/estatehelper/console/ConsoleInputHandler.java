package main.java.ru.innop.estatehelper.console;

import main.java.ru.innop.estatehelper.exceptions.RepeatPrimaryKeyException;
import main.java.ru.innop.estatehelper.factory.EstateFactory;
import main.java.ru.innop.estatehelper.factory.EstateFactoryImpl;
import main.java.ru.innop.estatehelper.model.LoginForm;
import main.java.ru.innop.estatehelper.repositories.EstateRepo;
import main.java.ru.innop.estatehelper.repositories.EstateRepoImpl;
import main.java.ru.innop.estatehelper.repositories.UserRepo;
import main.java.ru.innop.estatehelper.repositories.UserRepoImpl;
import main.java.ru.innop.estatehelper.service.EstateService;
import main.java.ru.innop.estatehelper.service.EstateServiceImpl;
import main.java.ru.innop.estatehelper.service.LoginSignUpService;
import main.java.ru.innop.estatehelper.service.LoginSignUpServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputHandler {
    private final UserRepo userRepo = new UserRepoImpl();
    private final EstateRepo estateRepo = new EstateRepoImpl();
    private final LoginSignUpService loginSignUpService = new LoginSignUpServiceImpl(userRepo);
    private final EstateService estateService = new EstateServiceImpl(estateRepo);
    private final EstateFactory estateFactory = new EstateFactoryImpl();

    private String lastState;
    private String currentState;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start() throws IOException {
        currentState = lastState = "greeting";
        boolean working = true;
        String login = null;
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
                        case "exit": {
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
                    System.out.println("If you wanna exit, type \"exit\", if you wanna logout, type \"greeting\"" +
                            "\n, if you wanna add new estate, type \"add-estate\"" +
                            "\n, if you wanna buy an estate, type \"buy-estate\"" +
                            "\n, if you wanna see your estates, type \"look-estates\"");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit":
                        case "greeting":
                        case "add-estate":
                        case "buy-estate":
                        case "look-estates":
                        case "sell-estate": {
                            lastState = currentState;
                            currentState = input;
                            continue;
                        }
                    }

                }
                case "signup": {
                    System.out.println("If you wanna exit type \"exit\", if you wanna return back type \"greeting\", if you wanna continue type anything else");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit": {
                            currentState = lastState = "exit";
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
                    } catch (RepeatPrimaryKeyException e) {
                        System.out.println("Failure. Try again");
                        continue;
                    }
                    System.out.println("Success. Hello " + login + "!");
                    lastState = currentState;
                    currentState = "menu";
                }
                case "look-estates": {
                    System.out.println("Your estates: ");
                    System.out.println(estateService.getEstatesBySeller(userRepo.findUserByLogin(login)));
                    System.out.println("If you wanna exit type \"exit\", if you wanna return back type \"menu\"");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit":
                        case "menu": {
                            currentState = lastState;
                            currentState = input;
                        }
                    }
                }
                case "add-estate": {
                    System.out.print("");
                }
            }
        }
    }
}
