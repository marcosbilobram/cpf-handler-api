package com.max.cpfhandler.repositories;

import com.max.cpfhandler.entities.InvalidCPF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidCpfRepository extends JpaRepository<InvalidCPF, Long > {
    //InvalidCPF findBycpf(String cpf);

    Void deleteByCpf(String cpf);
}
