package main.java.ru.innop.estatehelper.console;

import main.java.ru.innop.estatehelper.factory.EstateFactory;
import main.java.ru.innop.estatehelper.factory.EstateFlatFactory;
import main.java.ru.innop.estatehelper.factory.EstateHouseFactory;
import main.java.ru.innop.estatehelper.factory.EstateVillaFactory;
import main.java.ru.innop.estatehelper.model.Estate;
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
    private final EstateFactory houseFactory = new EstateHouseFactory();
    private final EstateFactory villaFactory = new EstateVillaFactory();
    private final EstateFactory flatFactory = new EstateFlatFactory();

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
                    System.out.println("If you want to exit type \"exit\", " +
                            "\nif you want to login type \"login\", " +
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
                    System.out.println("If you want to exit type \"exit\", " +
                            "\nif you want to return back type \"greeting\", " +
                            "\nif you want to continue type anything else");
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
                    System.out.println(" * If you want to exit, type \"exit\", " +
                            "\n * if you want to logout, type \"logout\"" +
                            "\n * if you want to add new estate, type \"add-estate\"" +
                            "\n * if you want to buy an estate, type \"buy-estate\"" +
                            "\n * if you want to see your estates, type \"look-estates\"");
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
                    System.out.println("If you want to exit type \"exit\", " +
                            "if you want to return back type \"greeting\", " +
                            "if you want to continue type anything else");
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
                    Double balance = tryInputDouble("How many money do you have: ");
                    try {
                        userRepo.saveUser(new User(login, password, email, number, balance));
                    } catch (Exception e) {
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
                    System.out.println("If you want to exit type \"exit\", if you want to return back type \"menu\"");
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
                                Integer countOfRoom = tryInputInt("Write count of rooms : ");
                                Integer space = tryInputInt("Write count of amount of space (in square foots) : ");
                                estateRepo.saveEstate(houseFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, countOfRoom, space));
                                break;
                            }
                            case "flat": {
                                Integer num = tryInputInt("Write number of people who lives in the flat : ");
                                estateRepo.saveEstate(flatFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, num));
                                break;
                            }
                            case "villa": {
                                Integer numOfPools = tryInputInt("Write number of pools : ");
                                Boolean hasBowling = tryInputBool("Write existing of pool (true / false) : ");
                                Integer numOfHelicopters = tryInputInt("Write number of helicopters in the villa : ");

                                estateRepo.saveEstate(villaFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, numOfPools, hasBowling, numOfHelicopters));
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
                    System.out.println("All estates : ");
                    for (int i = 0; i < estateRepo.findAll().size(); i++) {
                        Estate e = estateRepo.findAll().get(i);
                        System.out.println("Number " + i + " : " + e);
                    }
                    User user = userRepo.findUserByLogin(login);
                    int ind = tryInputInt("Please write a number of the estate you want to buy : ");
                    Estate e = estateRepo.getEstateById((long) ind);
                    if (user.getBalance() >= e.getPrice()) {

                    }
//todo
                    lastState = currentState;
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

    private Integer tryInputInt(String message) {
        int res = 0;
        while (true) {
            try {
                System.out.print(message);
                res = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong format. Try again");
            }
        }
        return res;
    }

    private Boolean tryInputBool(String message) {
        boolean res = false;
        while (true) {
            try {
                System.out.print(message);
                res = Boolean.parseBoolean(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong format. Try again");
            }
        }
        return res;
    }


}
