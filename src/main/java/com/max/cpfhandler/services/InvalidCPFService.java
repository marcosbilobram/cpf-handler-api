package com.max.cpfhandler.services;

import com.max.cpfhandler.entities.InvalidCPF;
import com.max.cpfhandler.repositories.InvalidCpfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvalidCPFService {

    @Autowired
    InvalidCpfRepository repo;

    public List<InvalidCPF> findAll(){
        return repo.findAll();
    }

    public InvalidCPF findById(Long id) {
        return  repo.findById(id).get();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void deleteByCpfNumber(String cpf){
        repo.deleteByCpf(cpf);
    }

    public InvalidCPF insert(InvalidCPF cpf) {
        return repo.save(cpf);
    }

    public InvalidCPF update(Long id, InvalidCPF cpf) {
        InvalidCPF inDBcpf = findById(id);
        dataUpdate(inDBcpf, cpf);
        return repo.save(inDBcpf);
    }

    public void dataUpdate(InvalidCPF inDBCpf, InvalidCPF cpf) {
        inDBCpf.setCpf(cpf.getCpf());
        inDBCpf.setCreatedAt(cpf.getCreatedAt());
    }

}
