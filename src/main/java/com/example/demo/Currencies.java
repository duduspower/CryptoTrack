package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Currencies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String simplyId;

    @NotBlank
    private String advancedId;

    public Currencies() {
    }

    public Currencies(int id, String advancedId, String simplyId) {
        this.advancedId = advancedId;
        this.simplyId = simplyId;
    }

    public String getAdvancedId() {
        return advancedId;
    }

    public void setAdvancedId(String advancedId) {
        this.advancedId = advancedId;
    }

    public String getSimplyId() {
        return simplyId;
    }

    public void setSimplyId(String simplyId) {
        this.simplyId = simplyId;
    }
}
