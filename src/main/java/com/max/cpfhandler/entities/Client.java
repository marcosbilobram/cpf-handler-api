package com.max.cpfhandler.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_client")
@SequenceGenerator(name="client", sequenceName = "SQ_TB_CLIENT", allocationSize = 1)
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client")
    private Long id;
    private String name;
    
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "client_cpf", length = 11)),
            @AttributeOverride(name = "createdAt", column = @Column(name = "createdAt"))
    })
    private CPF cpf;

    public Client(){}

    public Client(Long id, String name, CPF cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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
