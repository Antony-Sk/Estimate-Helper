package main.java.ru.innop.estatehelper.repositories;

import main.java.ru.innop.estatehelper.model.User;

public interface UserRepo {
    void saveUser(User user);
    void deleteUser(User user);
    User findUserByLogin(String login);
    void blockUser(User user);
}
