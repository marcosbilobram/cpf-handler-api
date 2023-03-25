package com.max.cpfhandler.dto;

import com.max.cpfhandler.entities.CPF;
import com.max.cpfhandler.entities.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClientDTO {

    private Long id;
    private String name;
    private CPF cpf;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
    }

}
