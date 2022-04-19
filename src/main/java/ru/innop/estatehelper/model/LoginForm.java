package main.java.ru.innop.estatehelper.model;

public class LoginForm {
    public String login;
    public String password;
    public String contactNumber;
    public String email;

    public LoginForm(String login, String password, String contactNumber, String email) {
        this.login = login;
        this.password = password;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public User generateUser() {
        return new User(login, password, contactNumber, email);
    }
}
