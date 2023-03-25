package com.max.cpfhandler.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_client")
@SequenceGenerator(name="client", sequenceName = "SQ_TB_CLIENT", allocationSize = 1)
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client")
    private Long id;
    private String name;
    
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cpf", column = @Column(name = "client_cpf", length = 11, unique = true)),
    })
    private CPF cpf;

}
