package by.etc.shop.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 6259612578312780114L;
    private int id;
    private String userName;
    private String login;
    private String password;
    private String email;

    public User() {
    }

    public User(int id, String userName, String login, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String userName, String login, String password, String email) {
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                this.userName.equals(user.userName) &&
                this.login.equals(user.login) &&
                this.password.equals(user.password) &&
                this.email.equals(user.email);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + userName.hashCode();
        result = prime * result + login.hashCode();
        result = prime * result + password.hashCode();
        result = prime * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
