package com.max.highriskcpf.repositories;

import com.max.highriskcpf.entities.HighRiskCPF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighRiskCpfRepository extends JpaRepository<HighRiskCPF, Long> {
    //InvalidCPF findBycpf(String cpf);

    //Void deleteByCpf(String cpf);

    //CPF getByCpf(String cpf);

    //@Query(value = "SELECT c FROM CPF c WHERE c.cpf.cpf = ?1 ")
    HighRiskCPF findCPFByCpfEquals(String cpf);
}
