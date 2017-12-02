package hr.foi.air.optimix.optimix.generators;

import hr.foi.air.optimix.model.Person;

/**
 * Created by Gloria Babić on 22.11.2017..
 */

public class UserNameGenerator {

    private String nameValue;
    private String surnameValue;

    public UserNameGenerator(String name, String surname) {

        nameValue = name;
        surnameValue = surname;
    }

    public String generateUsername(){

        String generatedUsername;
        String namePart;
        String surnamePart;

        namePart = (nameValue.toLowerCase()).substring(0,3);
        namePart = removeSpecialCharacters(namePart);

        surnamePart = surnameValue.toLowerCase();
        surnamePart = removeSpecialCharacters(surnamePart);

        generatedUsername = namePart + surnamePart;


        return generatedUsername;
    }

    public String removeSpecialCharacters(String string){

        if(string.contains("č")){
            string = string.replace("č", "c");
        }
        if(string.contains("ć")){
            string = string.replace("ć", "c");
        }
        if(string.contains("đ")){
            string = string.replace("đ", "dj");
        }
        if(string.contains("ž")){
            string = string.replace("ž", "z");
        }
        if(string.contains("š")){
            string = string.replace("š", "s");
        }

        return string;

    }
}
