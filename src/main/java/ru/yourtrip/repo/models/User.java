package ru.yourtrip.repo.models;

import java.io.Serializable;

public class User implements Serializable {

    private String login;
    private String hash;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getLogin() != null ? getLogin().equals(user.getLogin()) : user.getLogin() == null;
    }

    @Override
    public int hashCode() {
        return getLogin() != null ? getLogin().hashCode() : 0;
    }
}