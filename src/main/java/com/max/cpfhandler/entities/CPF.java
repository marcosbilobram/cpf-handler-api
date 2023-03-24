package com.max.cpfhandler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Calendar;

@Embeddable
public class CPF {

    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar createdAt;

    private boolean canBeaFraud;

    public CPF(){}

    public CPF(String cpf) {
        this.cpf = cpf;
        this.createdAt = Calendar.getInstance();
    }

    public CPF(String cpf, Calendar createdAt) {
        this.cpf = cpf;
        this.createdAt = createdAt;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isItaFraud() {
        return canBeaFraud;
    }

    public void setCanBeaFraud(boolean canBeaFraud) {
        this.canBeaFraud = canBeaFraud;
    }
}
