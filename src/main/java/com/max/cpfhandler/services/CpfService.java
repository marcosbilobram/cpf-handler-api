package com.max.cpfhandler.services;

import com.max.cpfhandler.exceptions.InvalidCpfException;
import org.springframework.stereotype.Service;

@Service
public class CpfService {

    public boolean validateCPF(String cpf) {

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
                return false;
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
                return false;
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
                return false;
            }

            return true;
        }catch (Exception e){
            throw new InvalidCpfException("CPF is not valid.");
        }

    }

    public String removeNotNumberCharacters(String cpf){
        try{
            String treatedCPF = cpf.replace(".", "").replace("-", "");

            return treatedCPF;
        }catch (Exception e){
            throw new InvalidCpfException("CPF is not valid.");
        }
    }
}
