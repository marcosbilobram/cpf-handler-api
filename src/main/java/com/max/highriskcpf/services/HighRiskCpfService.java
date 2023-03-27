package com.max.highriskcpf.services;

import com.max.highriskcpf.dto.HighRiskCpfDTO;
import com.max.highriskcpf.entities.HighRiskCPF;
import com.max.highriskcpf.exceptions.ExistsCpfException;
import com.max.highriskcpf.exceptions.InvalidCpfException;
import com.max.highriskcpf.exceptions.NotFoundCpfException;
import com.max.highriskcpf.repositories.HighRiskCpfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class HighRiskCpfService {

    @Autowired
    HighRiskCpfRepository repo;

    public List<HighRiskCPF> findAllFraudCPFs(){
        return repo.findAll();
    }

    public HighRiskCPF findCPFById(Long id) {
        return repo.findById(id).get();
    }

    public HighRiskCPF insert(HighRiskCPF cpf)  {
        if(!validateCPF(cpf.getCpf())){
            throw new InvalidCpfException("CPF is not valid.");
        }

        cpf.setCpf(removeNotNumberCharacters(cpf.getCpf()));
        cpf.setCreatedAt(Calendar.getInstance());

        HighRiskCPF cpfToSave = cpf;

        try {
            return repo.save(cpfToSave);
        }
        catch (DataIntegrityViolationException e){
            throw new ExistsCpfException("There is already a register with the CPF: " + cpfToSave.getCpf());
        }
        catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    public HighRiskCPF checkIfCpfIsInDB(String cpf){
        HighRiskCPF cpfInDB = findCpfWithValidation(cpf);
        boolean cpfExists = cpfInDB != null;
        if (cpfExists){
            return new HighRiskCPF(cpfInDB.getCpf(),
                                    cpfInDB.getCreatedAt());
        } else {
            throw new NotFoundCpfException("The CPF: " + cpf + " is not on the fraud list");
        }
    }

    public void removeCpfFromDbByCpfValue(String cpf){
        HighRiskCPF cpfInDB = findCpfWithValidation(cpf);
        repo.deleteById(cpfInDB.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public HighRiskCPF findCpfWithValidation(String cpf){
        validateCPF(cpf);
        String treatedCPF;

        treatedCPF = removeNotNumberCharacters(cpf);
        HighRiskCPF cpfInDB = repo.findCPFByCpfEquals(treatedCPF);

        boolean cpfExists = cpfInDB != null;
        if (!cpfExists){
            throw new NotFoundCpfException("Can't found the CPF:" + cpf + " in the database");
        }

        return cpfInDB;
    }

    public HighRiskCPF fromCPFDTO(HighRiskCpfDTO cpf) {
        HighRiskCPF cpfCrt = new HighRiskCPF(cpf.getCpf(), Calendar.getInstance());
        return cpfCrt;
    }

    private boolean validateCPF(String cpf) {

        try {
            cpf = removeNotNumberCharacters(cpf);

            if (cpf.length() != 11) {
                throw new InvalidCpfException("CPF is not valid.");
            }

            boolean todosDigitosIguais = true;
            for (int i = 1; i < cpf.length(); i++) {
                if (cpf.charAt(i) != cpf.charAt(0)) {
                    todosDigitosIguais = false;
                    break;
                }
            }
            if (todosDigitosIguais) {
                throw new InvalidCpfException("CPF is not valid.");
            }

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma += digito * (10 - i);
            }

            int resto = soma % 11;
            int digitoVerificador1;
            if (resto < 2) {
                digitoVerificador1 = 0;
            } else {
                digitoVerificador1 = 11 - resto;
            }

            if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9))) {
                throw new InvalidCpfException("CPF is not valid.");
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma += digito * (11 - i);
            }
            resto = soma % 11;
            int digitoVerificador2;
            if (resto < 2) {
                digitoVerificador2 = 0;
            } else {
                digitoVerificador2 = 11 - resto;
            }

            if (digitoVerificador2 != Character.getNumericValue(cpf.charAt(10))) {
                throw new InvalidCpfException("CPF is not valid.");
            }

            return true;
        }catch (Exception e){
            throw new InvalidCpfException("CPF is not valid.");
        }

    }

    private String removeNotNumberCharacters(String cpf){
        try{
            String treatedCPF = cpf.replace(".", "").replace("-", "");

            return treatedCPF;
        }catch (Exception e){
            throw new InvalidCpfException("CPF is not valid.");
        }
    }
}
