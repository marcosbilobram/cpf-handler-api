package com.max.highriskcpf.repositories;

import com.max.highriskcpf.entities.HighRiskCPF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighRiskCpfRepository extends JpaRepository<HighRiskCPF, Long> {

    //@Query(value = "SELECT c FROM CPF c WHERE c.cpf.cpf = ?1 ")
    HighRiskCPF findCPFByCpfEquals(String cpf);
}
