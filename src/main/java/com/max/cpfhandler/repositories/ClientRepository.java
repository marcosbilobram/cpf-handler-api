package com.max.cpfhandler.repositories;

import com.max.cpfhandler.entities.CPF;
import com.max.cpfhandler.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    //InvalidCPF findBycpf(String cpf);

    //Void deleteByCpf(String cpf);

    //CPF getByCpf(String cpf);

    @Query(value = "SELECT c FROM Client c WHERE c.cpf.cpf = ?1 ")
    Client findClientThatEqualsCpfValue(String cpf);

    @Query(value = "SELECT c.cpf FROM Client c")
    List<CPF> findAllFraudCPFs();
}
