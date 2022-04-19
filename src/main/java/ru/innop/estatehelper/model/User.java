package main.java.ru.innop.estatehelper.model;

public class User {
    private String login;
    private String password;
    private String contactNumber;
    private String email;
    private UserRole role;

    public User(String login, String password, String contactNumber, String email) {
        this.login = login;
        this.password = password;
        this.contactNumber = contactNumber;
        this.email = email;
        role = UserRole.USER;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}
