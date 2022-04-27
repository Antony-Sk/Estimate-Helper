package main.java.ru.innop.estatehelper.repositories;

import main.java.ru.innop.estatehelper.model.User;
import main.java.ru.innop.estatehelper.model.UserRole;

import java.util.ArrayList;
import java.util.Objects;

public class UserRepoImpl implements UserRepo {
    private final ArrayList<User> storage = new ArrayList<>();

    @Override
    public void blockUser(User user) {
        user.setRole(UserRole.DELETED);
    }

    public UserRepoImpl() {
        storage.add(new User("admin", "admin", "88005553535", "admin@mail.ru", Double.MAX_VALUE)); // Secret admin
        storage.get(0).setRole(UserRole.ADMIN);
    }

    @Override
    public void saveUser(User user) {
        storage.add(user);
    }

    @Override
    public void deleteUser(User user) {
        if (findUserByLogin(user.getLogin()) != null)
            storage.remove(user);
    }

    @Override
    public User findUserByLogin(String login) {
        for (User u : storage) {
            if (Objects.equals(u.getLogin(), login))
                return u;
        }
        return null;
    }
}
