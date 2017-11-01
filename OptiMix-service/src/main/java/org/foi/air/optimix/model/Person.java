/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gloria Babić
 */
@Entity
@Table(name="person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    long idPerson;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "authority_level")
    long authority;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;

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

    public List<ActivityLog> getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(List<ActivityLog> activityLog) {
        this.activityLog = activityLog;
    }

    public List<AnalysisLog> getAnalysisLog() {
        return analysisLog;
    }

    public void setAnalysisLog(List<AnalysisLog> analysisLog) {
        this.analysisLog = analysisLog;
    }

    public List<RecipeLog> getRecipeLog() {
        return recipeLog;
    }

    public void setRecipeLog(List<RecipeLog> recipeLog) {
        this.recipeLog = recipeLog;
    }
    
    

    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<ActivityLog> activityLog;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<AnalysisLog> analysisLog;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<RecipeLog> recipeLog;

    public List<ActivityLog> getUserActivity() {
        return activityLog;
    }

    public void setUserActivity(List<ActivityLog> userActivity) {
        this.activityLog = userActivity;
    }

    public List<AnalysisLog> getUserAnalysis() {
        return analysisLog;
    }

    public void setUserAnalysis(List<AnalysisLog> userAnalysis) {
        this.analysisLog = userAnalysis;
    }

    public List<RecipeLog> getUserRecipe() {
        return recipeLog;
    }

    public void setUserRecipe(List<RecipeLog> userRecipe) {
        this.recipeLog = userRecipe;
    }

    

    public List<ActivityLog> getUser() {
        return activityLog;
    }

    public void setUser(List<ActivityLog> user) {
        this.activityLog = user;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
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

    public long getAuthority() {
        return authority;
    }

    public void setAuthority(long authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return this.username + " " + this.password;
    }

}
