package com.max.cpfhandler.dto;

import com.max.cpfhandler.entities.CPF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CpfDTO {

    private String cpf;

    private Calendar createdAt;

    public CpfDTO(CPF cpf) {
        this.cpf = cpf.getCpf();
        this.createdAt = cpf.getCreatedAt();
    }
}
