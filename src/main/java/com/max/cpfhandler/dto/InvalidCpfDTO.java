package com.max.cpfhandler.dto;

import com.max.cpfhandler.entities.InvalidCPF;

import java.util.Calendar;

public class InvalidCpfDTO {

    private Long id;

    private String cpf;

    private Calendar creationDate;

    public InvalidCpfDTO() {}

    public InvalidCpfDTO(InvalidCPF cpf) {
        this.id = cpf.getId();
        this.cpf = cpf.getCpf();
        this.creationDate = cpf.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }
}
