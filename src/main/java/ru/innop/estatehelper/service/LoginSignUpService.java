package main.java.ru.innop.estatehelper.service;

import main.java.ru.innop.estatehelper.model.LoginForm;

public interface LoginSignUpService {
    void sighUpNewUser(LoginForm loginForm);
    boolean tryToLogin(String login, String password);
}
