package main.java.ru.innop.estatehelper.console;

import main.java.ru.innop.estatehelper.exceptions.RepeatPrimaryKeyException;
import main.java.ru.innop.estatehelper.factory.EstateFactory;
import main.java.ru.innop.estatehelper.factory.EstateFactoryImpl;
import main.java.ru.innop.estatehelper.model.EstateType;
import main.java.ru.innop.estatehelper.model.User;
import main.java.ru.innop.estatehelper.repositories.EstateRepo;
import main.java.ru.innop.estatehelper.repositories.EstateRepoImpl;
import main.java.ru.innop.estatehelper.repositories.UserRepo;
import main.java.ru.innop.estatehelper.repositories.UserRepoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputHandler {
    private final UserRepo userRepo = new UserRepoImpl();
    private final EstateRepo estateRepo = new EstateRepoImpl();
    private final EstateFactory estateFactory = new EstateFactoryImpl();

    private String lastState;
    private String currentState;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start() throws IOException {
        currentState = lastState = "greeting";
        boolean working = true;
        String login = null;
        while (working) {
            switch (currentState) {
                case "greeting": {
                    System.out.println("Hello!");
                    System.out.println("It is the Estate Helper!");
                    System.out.println("This system helps you to sell or buy property");
                    System.out.println("If you wanna exit type \"exit\", " +
                            "\nif you wanna login type \"login\", " +
                            "\nif you are here for the first time, please sigh up: (type \"signup\"): ");
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
                    System.out.println("If you wanna exit type \"exit\", " +
                            "\nif you wanna return back type \"greeting\", " +
                            "\nif you wanna continue type anything else");
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
                    User user = userRepo.findUserByLogin(login);
                    boolean isAuth = user != null && user.getPassword().equals(password);
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
                    System.out.println(" * If you wanna exit, type \"exit\", " +
                            "\n * if you wanna logout, type \"logout\"" +
                            "\n * if you wanna add new estate, type \"add-estate\"" +
                            "\n * if you wanna buy an estate, type \"buy-estate\"" +
                            "\n * if you wanna see your estates, type \"look-estates\"");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit":
                        case "add-estate":
                        case "buy-estate":
                        case "look-estates":
                        case "sell-estate": {
                            lastState = currentState;
                            currentState = input;
                            continue;
                        }
                        case "logout": {
                            lastState = currentState;
                            currentState = "greeting";
                            continue;
                        }
                    }
                    System.out.println("Wrong command. Try again");
                }
                case "signup": {
                    System.out.println("If you wanna exit type \"exit\", " +
                            "if you wanna return back type \"greeting\", " +
                            "if you wanna continue type anything else");
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
                        userRepo.saveUser(new User(login, password, email, number));
                    } catch (RepeatPrimaryKeyException e) {
                        System.out.println("Failure. Try again");
                        continue;
                    }
                    System.out.println("Success. Hello " + login + "!");
                    lastState = currentState;
                    currentState = "menu";
                    continue;
                }
                case "look-estates": {
                    System.out.println("Your estates: ");
                    System.out.println(estateRepo.findAllBySeller(userRepo.findUserByLogin(login)));
                    System.out.println("If you wanna exit type \"exit\", if you wanna return back type \"menu\"");
                    String input = reader.readLine();
                    switch (input) {
                        case "exit":
                        case "menu": {
                            currentState = lastState;
                            currentState = input;
                        }
                    }
                    continue;
                }
                case "add-estate": {
                    System.out.print("Please write type of estate: (house, villa, flat)");
                    String input = reader.readLine();
                    System.out.print("Please write description of the estate: ");
                    String description = reader.readLine();
                    System.out.print("Please write address of the estate: ");
                    String address = reader.readLine();
                    Double price = tryInputDouble("Please write the price of the estate in rubbles: ");
                    try {
                        switch (input) {
                            case "house": {
                                System.out.print("Write count of rooms : ");
                                String countOfRoom = reader.readLine();
                                System.out.print("Write count of amount of space (in sq ft) : ");
                                String space = reader.readLine();
                                estateRepo.saveEstate(estateFactory.createEstate(EstateType.HOUSE, description, userRepo.findUserByLogin(login), address, price, Integer.parseInt(countOfRoom), Integer.parseInt(space)));
                                break;
                            }
                            case "flat": {
                                System.out.print("Write count of rooms : ");
                                String countOfRoom = reader.readLine();
                                System.out.print("Write count of amount of space (in sq ft) : ");
                                String space = reader.readLine();
                                estateRepo.saveEstate(estateFactory.createEstate(EstateType.FLAT, description, userRepo.findUserByLogin(login), address, price, Integer.parseInt(countOfRoom), Integer.parseInt(space)));
                                break;
                            }
                            case "villa": {
                                System.out.print("Write count of rooms : ");
                                String countOfRoom = reader.readLine();
                                System.out.print("Write count of amount of space (in square foots) : ");
                                String space = reader.readLine();
                                estateRepo.saveEstate(estateFactory.createEstate(EstateType.VILLA, description, userRepo.findUserByLogin(login), address, price, Integer.parseInt(countOfRoom), Integer.parseInt(space)));
                                break;
                            }
                        }
                        System.out.println("New estate added");
                        lastState = currentState;
                        currentState = "menu";
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Something wrong");
                    }
                    continue;
                }
                case "buy-estate": {
                    // TODO print estates

                    currentState = "menu";
                    continue;
                }
                default: {
                    currentState = lastState;
                }
            }
        }
    }

    private Double tryInputDouble(String message) {
        double res = 0.;
        while (true) {
            try {
                System.out.print(message);
                res = Double.parseDouble(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong format. Try again");
            }
        }
        return res;
    }
}
