package com.max.cpfhandler.dto;

import com.max.cpfhandler.entities.CPF;
import org.springframework.data.annotation.Transient;

import java.util.Calendar;

public class CpfDTO {

    private String cpf;

    private Calendar createdAt;

    private boolean canBeaFraud;

    public CpfDTO() {}

    public CpfDTO(CPF cpf) {
        this.cpf = cpf.getCpf();
        this.createdAt = cpf.getCreatedAt();
    }

    public CpfDTO(String cpf, Calendar createdAt) {
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

    public boolean isCanBeaFraud() {
        return canBeaFraud;
    }

    public void setCanBeaFraud(boolean canBeaFraud) {
        this.canBeaFraud = canBeaFraud;
    }
}
