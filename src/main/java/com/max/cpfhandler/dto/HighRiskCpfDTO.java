package com.max.cpfhandler.dto;

import com.max.cpfhandler.entities.HighRiskCPF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HighRiskCpfDTO {

    private String cpf;

    private Calendar createdAt;

    public HighRiskCpfDTO(HighRiskCPF cpf) {
        this.cpf = cpf.getCpf();
        this.createdAt = cpf.getCreatedAt();
    }
}
