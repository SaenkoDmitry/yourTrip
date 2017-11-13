package ru.yourtrip.repo.models;

import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;

@Entity
public class Person implements Serializable {
    public static final Key key = MacProvider.generateKey();

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login", unique = true, nullable = false, length = 100)
    private String login;

    @Column(name = "nickname", unique = true, nullable = false, length = 100)
    private String nickname;

    @Column(name = "avatar", unique = true, nullable = false, length = 200)
    private String avatar;

    @Column(name = "hash", nullable = false, length = 100)
    private String hash;

    @Column(name = "mail", nullable = false, length = 255)
    private String mail;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "hidden_nickname", nullable = false)
    private Boolean hidden_nickname;

    @Column(name = "hidden_mail", nullable = false)
    private Boolean hidden_mail;

    @Column(name = "hidden_birthday", nullable = false)
    private Boolean hidden_birthday;

    public Person() { }

    public Person(String login, String nickname, String avatar, String hash, String mail, Date birthday, String role, Boolean hidden_nickname, Boolean hidden_mail, Boolean hidden_birthday) {
        this.login = login;
        this.nickname = nickname;
        this.avatar = avatar;
        this.hash = hash;
        this.mail = mail;
        this.birthday = birthday;
        this.role = role;
        this.hidden_nickname = hidden_nickname;
        this.hidden_mail = hidden_mail;
        this.hidden_birthday = hidden_birthday;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHash() {
        return hash;
    }

    public String getMail() {
        return mail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", hash='" + hash + '\'' +
                ", mail='" + mail + '\'' +
                ", birthday=" + birthday +
                ", role='" + role + '\'' +
                ", hidden_nickname=" + hidden_nickname +
                ", hidden_mail=" + hidden_mail +
                ", hidden_birthday=" + hidden_birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != null ? !getId().equals(person.getId()) : person.getId() != null) return false;
        return getLogin() != null ? getLogin().equals(person.getLogin()) : person.getLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }
}