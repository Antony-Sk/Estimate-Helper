package main.java.ru.innop.estatehelper.service;

import main.java.ru.innop.estatehelper.model.LoginForm;
import main.java.ru.innop.estatehelper.model.User;
import main.java.ru.innop.estatehelper.repositories.UserRepo;

public class LoginSignUpServiceImpl implements LoginSignUpService {
    private final UserRepo userRepo;

    public LoginSignUpServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void sighUpNewUser(LoginForm loginForm) {
        userRepo.saveUser(loginForm.generateUser());
    }

    @Override
    public boolean tryToLogin(String login, String password) {
        User user = userRepo.findUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }
}
