package hr.foi.air.optimix.model;

import java.io.Serializable;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class Person implements Serializable {

    long idPerson;
    String name;
    String surname;
    String username;
    String password;
    long authority;

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Person(String name, String surname, String username, String password) {
        this.name = name;
        this.surname =surname;
        this.username = username;
        this.password = password;
    }
    public Person(String name, String surname, String username, String password, long authority) {
        this.name = name;
        this.surname =surname;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public long getAuthority() {
        return authority;
    }

    public void setAuthority(long authority) {
        this.authority = authority;
    }

    public Person(long idPerson) {
        this.idPerson = idPerson;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return this.username + " " + this.password;
    }


}
