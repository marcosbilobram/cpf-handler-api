package com.max.highriskcpf.repositories;

import com.max.highriskcpf.entities.HighRiskCPF;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class HighRiskCpfRepositoryTest {

    @Autowired
    private HighRiskCpfRepository repository;

    @Test
    public void mustReturnAllHighRiskCPFsInDB() throws Exception {
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").build();
        HighRiskCPF cpf2 = HighRiskCPF.builder().cpf("02030236098").build();

        cpf = repository.save(cpf);
        cpf2 = repository.save(cpf2);
        repository.flush();

        List<HighRiskCPF> cpfList = repository.findAll();

        Assertions.assertThat(cpfList).isNotNull();
        Assertions.assertThat(cpfList).isNotEmpty();
        Assertions.assertThat(cpfList.size()).isGreaterThan(1);

    }

    @Test
    public void mustCheckIfTheGivenCPFIsInDB() throws Exception {
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").build();
        HighRiskCPF cpf2 = HighRiskCPF.builder().cpf("02030236098").build();

        cpf = repository.save(cpf);
        cpf2 = repository.save(cpf2);
        repository.flush();

        HighRiskCPF cpfReturned = repository.findCPFByCpfEquals(cpf2.getCpf());

        Assertions.assertThat(cpfReturned).isNotNull();
        Assertions.assertThat(cpf2.getId()).isEqualTo(cpfReturned.getId());

    }

    @Test
    public void mustInsertNewHighRiskCPFInFraudList() throws Exception {
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").build();

        HighRiskCPF savedCPF = repository.save(cpf);
        repository.flush();

        Assertions.assertThat(repository.findAll()).isNotEmpty();
        Assertions.assertThat(savedCPF).isNotNull();
        Assertions.assertThat(savedCPF.getId()).isGreaterThan(0);

    }

    @Test
    public void mustDeleteTheHighRiskCPFInDbByCpfValue() throws Exception {
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").build();

        repository.save(cpf);
        repository.flush();

        repository.deleteById(cpf.getId());
        HighRiskCPF cpfReturned = repository.findCPFByCpfEquals(cpf.getCpf());
        repository.flush();

        Assertions.assertThat(cpfReturned).isNull();

    }

}
