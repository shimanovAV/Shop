package by.etc.shop.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 6259612578312780114L;

    private String userName;
    private String login;
    private String email;
    private String address;
    private Date birthday;
    private int numberOfCard;
    private String phoneNumber;
    private boolean accessLevel;

    public User() {
    }

    public User(String userName, String login, String email, String address, Date birthday, int numberOfCard, String phoneNumber, boolean accessLevel) {
        this.userName = userName;
        this.login = login;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.numberOfCard = numberOfCard;
        this.phoneNumber = phoneNumber;
        this.accessLevel = accessLevel;
    }

    public User(String userName, String login, String email, Date birthday) {
        this.userName = userName;
        this.login = login;
        this.email = email;
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(boolean accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setNumberOfCard(int numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return numberOfCard == user.numberOfCard &&
                userName.equals(user.userName) &&
                login.equals(user.login) &&
                email.equals(user.email) &&
                address.equals(user.address) &&
                birthday.equals(user.birthday) &&
                phoneNumber.equals(user.phoneNumber)&&
                accessLevel==user.accessLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numberOfCard;
        result = prime * result + userName.hashCode();
        result = prime * result + login.hashCode();
        result = prime * result + email.hashCode();
        result = prime * result + address.hashCode();
        result = prime * result + birthday.hashCode();
        result = prime * result + phoneNumber.hashCode();
        result = prime * result + ((Boolean)accessLevel).compareTo(false);
        return result; }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", numberOfCard=" + numberOfCard +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
