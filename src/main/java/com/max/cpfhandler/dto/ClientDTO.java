package com.max.cpfhandler.dto;

import com.max.cpfhandler.entities.CPF;
import com.max.cpfhandler.entities.Client;

public class ClientDTO {

    private Long id;
    private String name;
    private CPF cpf;

    public ClientDTO(){}

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
}
