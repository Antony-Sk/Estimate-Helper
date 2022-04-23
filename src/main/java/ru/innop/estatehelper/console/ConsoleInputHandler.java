package main.java.ru.innop.estatehelper.console;

import main.java.ru.innop.estatehelper.factory.EstateFactory;
import main.java.ru.innop.estatehelper.factory.EstateFlatFactory;
import main.java.ru.innop.estatehelper.factory.EstateHouseFactory;
import main.java.ru.innop.estatehelper.factory.EstateVillaFactory;
import main.java.ru.innop.estatehelper.model.*;
import main.java.ru.innop.estatehelper.repositories.EstateRepo;
import main.java.ru.innop.estatehelper.repositories.EstateRepoImpl;
import main.java.ru.innop.estatehelper.repositories.UserRepo;
import main.java.ru.innop.estatehelper.repositories.UserRepoImpl;

import java.io.BufferedReader;
import java.io.Flushable;
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

    public ConsoleInputHandler() {
        estateRepo.saveEstate(houseFactory.createEstate("Cool house", userRepo.findUserByLogin("admin"), "South of Hell St., 69", 999999999.99, 10, 666));
        estateRepo.saveEstate(villaFactory.createEstate("Luxury Villa <3", userRepo.findUserByLogin("admin"), "Paradise St., 420", 420691488.22, 2, true, 100));
        estateRepo.saveEstate(flatFactory.createEstate("Dream dorm room >3", userRepo.findUserByLogin("admin"), "Daumana St, 228", -646., 4));
    }

    public void start() throws IOException {
        currentState = lastState = "greeting";
        boolean working = true;
        String login = null;
        boolean isAuth = false;
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
                    System.out.print("Type your login: ");
                    login = reader.readLine();
                    System.out.print("Type your password: ");
                    String password = reader.readLine();
                    User user = userRepo.findUserByLogin(login);
                    isAuth = user != null && user.getPassword().equals(password);
                    if (isAuth) {
                        System.out.println("Success. Hello " + login + "!");
                        lastState = currentState;
                        currentState = "menu";
                    } else {
                        System.out.println("Failure. Try again");
                        System.out.println("If you want to exit type \"exit\", " +
                                "\nif you want to return back type \"greeting\", " +
                                "\nif you want to retry type anything else");
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
                    }
                    continue;
                }
                case "menu": {
                    System.out.println(" * If you want to exit, type \"exit\", " +
                            "\n * if you want to logout, type \"logout\"" +
                            "\n * if you want to add new estate, type \"sell-estate\"" +
                            "\n * if you want to buy an estate, type \"buy-estate\"" +
                            "\n * if you want to see your estates, type \"look-estates\"");
                    if (userRepo.findUserByLogin(login).getRole() == UserRole.ADMIN) {
                        System.out.println("Admin panel\n * if tou want to delete estate, type \"delete-estate\"" +
                                "\n * if you want to change estate, type \"update-estate\"");
                    }
                    String input = reader.readLine();
                    switch (input) {
                        case "exit":
                        case "buy-estate":
                        case "look-estates":
                        case "delete-estate":
                        case "update-estate":
                        case "sell-estate": {
                            lastState = currentState;
                            currentState = input;
                            continue;
                        }
                        case "logout": {
                            lastState = currentState;
                            isAuth = false;
                            currentState = "greeting";
                            continue;
                        }
                    }
                    System.out.println("Wrong command. Try again");
                    continue;
                }
                case "signup": {
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
                        System.out.println("If you want to exit type \"exit\", " +
                                "if you want to return back type \"logout\", " +
                                "if you want to retry type anything else");
                        String input = reader.readLine();
                        switch (input) {
                            case "exit": {
                                currentState = lastState = "exit";
                                continue;
                            }
                            case "logout": {
                                currentState = lastState = "greeting";
                                isAuth = false;
                                continue;
                            }
                        }
                        continue;
                    }
                    System.out.println("Success. Hello " + login + "!");
                    isAuth = true;
                    lastState = currentState;
                    currentState = "menu";
                    continue;
                }
                case "look-estates": {
                    System.out.println("Your estates: ");
                    for (int i = 0; i < estateRepo.findAllBySeller(userRepo.findUserByLogin(login)).size(); i++) {
                        Estate e = estateRepo.findAllBySeller(userRepo.findUserByLogin(login)).get(i);
                        System.out.println("Number " + i + " : " + e);
                    }
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
                case "sell-estate": {
                    System.out.print("Please write type of estate: (house, villa, flat)");
                    String input = reader.readLine();
                    try {
                        switch (input) {
                            case "house": {
                                System.out.print("Please write description of the estate: ");
                                String description = reader.readLine();
                                System.out.print("Please write address of the estate: ");
                                String address = reader.readLine();
                                Double price = tryInputDouble("Please write the price of the estate in rubbles: ");
                                Integer countOfRoom = tryInputInt("Write count of rooms : ");
                                Integer space = tryInputInt("Write count of amount of space (in square foots) : ");
                                estateRepo.saveEstate(houseFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, countOfRoom, space));
                                break;
                            }
                            case "flat": {
                                System.out.print("Please write description of the estate: ");
                                String description = reader.readLine();
                                System.out.print("Please write address of the estate: ");
                                String address = reader.readLine();
                                Double price = tryInputDouble("Please write the price of the estate in rubbles: ");
                                Integer num = tryInputInt("Write number of residents in the flat : ");
                                estateRepo.saveEstate(flatFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, num));
                                break;
                            }
                            case "villa": {
                                System.out.print("Please write description of the estate: ");
                                String description = reader.readLine();
                                System.out.print("Please write address of the estate: ");
                                String address = reader.readLine();
                                Double price = tryInputDouble("Please write the price of the estate in rubbles: ");
                                Integer numOfPools = tryInputInt("Write number of pools : ");
                                Boolean hasBowling = tryInputBool("Write existing of pool (true / false) : ");
                                Integer numOfHelicopters = tryInputInt("Write number of helicopters in the villa : ");
                                estateRepo.saveEstate(villaFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, numOfPools, hasBowling, numOfHelicopters));
                                break;
                            }
                            default: {
                                System.out.println("Unknown type. Retry");
                                continue;
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
                    boolean found = false;
                    for (int i = 0; i < estateRepo.findAll().size(); i++) {
                        Estate e = estateRepo.findAll().get(i);
                        if (!e.getSeller().getLogin().equals(login)) {
                            System.out.println("Number " + i + " : " + e);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Nothing to buy!");
                        currentState = "menu";
                        continue;
                    }
                    User user = userRepo.findUserByLogin(login);
                    int ind = tryInputInt("Please write a number of the estate you want to buy : ");
                    Estate e = estateRepo.getEstateById((long) ind);
                    if (user.getBalance() >= e.getPrice()) {
                        user.setBalance(user.getBalance() - e.getPrice());
                        e.getSeller().setBalance(e.getSeller().getBalance() + e.getPrice());
                        e.setSeller(user);
                        System.out.println("Success");
                    } else {
                        System.out.println("Failure");
                    }
                    lastState = currentState;
                    currentState = "menu";
                    continue;
                }
                case "delete-estate": {
                    if (userRepo.findUserByLogin(login).getRole() == UserRole.ADMIN) {
                        System.out.println("All estates : ");
                        for (int i = 0; i < estateRepo.findAll().size(); i++) {
                            Estate e = estateRepo.findAll().get(i);
                            System.out.println("Number " + i + " : " + e);
                        }
                        int ind = tryInputInt("Please write a number of the estate you want to delete : ");
                        estateRepo.deleteEstate((long) ind);
                        lastState = currentState;
                        currentState = "menu";
                        continue;
                    }
                    currentState = lastState;
                    continue;
                }
                case "update-estate": {
                    if (userRepo.findUserByLogin(login).getRole() == UserRole.ADMIN) {
                        System.out.println("All estates : ");
                        for (int i = 0; i < estateRepo.findAll().size(); i++) {
                            Estate e = estateRepo.findAll().get(i);
                            System.out.println("Number " + i + " : " + e);
                        }
                        int ind = tryInputInt("Please write a number of the estate you want to update : ");
                        Estate e = estateRepo.getEstateById((long) ind);
                        estateRepo.deleteEstate((long) ind);

                        System.out.print("Please write description of the estate: ");
                        String description = reader.readLine();
                        System.out.print("Please write address of the estate: ");
                        String address = reader.readLine();
                        Double price = tryInputDouble("Please write the price of the estate in rubbles: ");
                        try {
                            if (e instanceof HouseEstate) {
                                Integer countOfRoom = tryInputInt("Write count of rooms : ");
                                Integer space = tryInputInt("Write count of amount of space (in square foots) : ");
                                estateRepo.saveEstate(houseFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, countOfRoom, space));
                                break;
                            }
                            if (e instanceof FLatEstate) {
                                Integer num = tryInputInt("Write number of residents in the flat : ");
                                estateRepo.saveEstate(flatFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, num));
                                break;
                            }
                            if (e instanceof VillaEstate) {
                                Integer numOfPools = tryInputInt("Write number of pools : ");
                                Boolean hasBowling = tryInputBool("Write existing of pool (true / false) : ");
                                Integer numOfHelicopters = tryInputInt("Write number of helicopters in the villa : ");
                                estateRepo.saveEstate(villaFactory.createEstate(description, userRepo.findUserByLogin(login), address, price, numOfPools, hasBowling, numOfHelicopters));
                                break;
                            }
                            System.out.println("Success");
                        } catch (Exception ex) {
                            System.out.println("Something wrong");
                            estateRepo.saveEstate(e);
                        }
                        lastState = currentState;
                        currentState = "menu";
                        continue;
                    }
                    currentState = lastState;
                    continue;
                }

                default: {
                    if (!isAuth)
                        currentState = "greeting";
                    else currentState = "menu";
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
