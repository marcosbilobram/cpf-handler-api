package com.max.cpfhandler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name="cpf", sequenceName = "SQ_TB_HIGH_RISK_CPF", allocationSize = 1)
@Table(name = "tb_high_risk_cpf")
public class HighRiskCPF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cpf")
    private Long id;

    @Column(length = 11, unique = true)
    private String cpf;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar createdAt;

    public HighRiskCPF(String cpf) {
        this.cpf = cpf;
        this.createdAt = Calendar.getInstance();
    }

    public HighRiskCPF(String cpf, Calendar createdAt) {
        this.cpf = cpf;
        this.createdAt = createdAt;
    }
}
