package com.max.cpfhandler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
public class CPF {

    @Column(length = 11, unique = true)
    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar createdAt;


    public CPF(String cpf) {
        this.cpf = cpf;
        this.createdAt = Calendar.getInstance();
    }
}
