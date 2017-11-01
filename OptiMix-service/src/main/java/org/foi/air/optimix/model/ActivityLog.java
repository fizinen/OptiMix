/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Gloria Babić
 */
@Entity
@Table(name = "activity_log")
public class ActivityLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activity_log")
    long idActivityLog;

    @Column(name = "activity")
    private String activity;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_person")
    private Person userId;

    public long getIdActivityLog() {
        return idActivityLog;
    }

    public void setIdActivityLog(long idActivityLog) {
        this.idActivityLog = idActivityLog;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Person getUser() {
        return userId;
    }

    public void setUser(Person user) {
        this.userId = user;
    }

}
