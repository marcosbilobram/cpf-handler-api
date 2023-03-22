package com.max.cpfhandler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;

@Entity
@Table(name = "tb_invalid_cpf")
@SequenceGenerator(name="invalidCPF", sequenceName = "SQ_INVALID_CPF", allocationSize = 1)
public class InvalidCPF {

    @Id
    @Column(name = "id_invalid_cpf")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invalidCPF")
    private Long id;

    @Column(name = "nr_cpf")
    private String cpf;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "dt_created_at")
    private Calendar createdAt;

    public InvalidCPF(){}

    public InvalidCPF(Long id, String cpf) {
        this.id = id;
        this.cpf = cpf;
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

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }
}
