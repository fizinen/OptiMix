package hr.foi.air.optimix.optimix.generators;

import hr.foi.air.optimix.model.Person;

/**
 * Created by Gloria Babić on 22.11.2017..
 */

/**
 * PasswordGenerator generates username with all small & big letters.
 */
public class UserNameGenerator {

    private String nameValue;
    private String surnameValue;

    /**
     * Constructor of Username generator
     * @param name {@code String} of persons name
     * @param surname {@code String} of persons surname
     */
    public UserNameGenerator(String name, String surname) {

        nameValue = name;
        surnameValue = surname;
    }

    /**
     * Generates username for person
     * @return generatedUsername generates {@code String} of generated username
     */
    public String generateUsername(){

        String generatedUsername;
        String namePart;
        String surnamePart;

        if(nameValue.length() < 3){
            namePart = (nameValue.toLowerCase()).substring(0,1);
        }

        namePart = (nameValue.toLowerCase()).substring(0,3);
        namePart = removeSpecialCharacters(namePart);

        surnamePart = surnameValue.toLowerCase();
        surnamePart = removeSpecialCharacters(surnamePart);

        generatedUsername = namePart + surnamePart;


        return generatedUsername;
    }
    /**
     * Removes special characters of username
     * @param string of name {@code String} to remove special characters
     * @return generatedUsername generates {@code String} of generated username
     */
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
