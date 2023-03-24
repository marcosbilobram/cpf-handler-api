package com.max.cpfhandler.services;

import org.springframework.stereotype.Service;

@Service
public class CpfService {

    public boolean validateCPF(String cpf) {
        cpf = removeNotNumberCharacters(cpf);

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }

        int firstDigit = 11 - (soma % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }

        soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }
        soma += firstDigit * 2;

        int secondDigit = 11 - (soma % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }

        return cpf.charAt(9) == Character.forDigit(firstDigit, 10)
                && cpf.charAt(10) == Character.forDigit(secondDigit, 10);
    }

    public String removeNotNumberCharacters(String cpf){
        return cpf = cpf.replace(".", "").replace("-", "");
    }
}
