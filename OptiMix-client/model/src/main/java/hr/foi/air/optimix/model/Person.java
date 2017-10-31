package hr.foi.air.optimix.model;

import java.io.Serializable;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class Person implements Serializable {

    long idPerson;
    String username;
    String password;

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return this.username + " " + this.password;
    }


}
