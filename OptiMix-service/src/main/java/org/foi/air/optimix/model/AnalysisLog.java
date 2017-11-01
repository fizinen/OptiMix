/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name="analysis_log")
public class AnalysisLog implements Serializable{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_analysis_log")
    long idAnalysisLog;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_person")
    private Person userId;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_analysis")
    private Analysis analysisId;
    
    @Column(name = "input_date")
    private Date inputDate;

    public Analysis getAnalysis() {
        return analysisId;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysisId = analysis;
    }

    public long getIdAnalysisLog() {
        return idAnalysisLog;
    }

    public void setIdAnalysisLog(long idAnalysisLog) {
        this.idAnalysisLog = idAnalysisLog;
    }

    public Person getUser() {
        return userId;
    }

    public void setUser(Person user) {
        this.userId = user;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    

    
}
