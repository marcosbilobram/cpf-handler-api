package com.max.highriskcpf.services;

import com.max.highriskcpf.entities.HighRiskCPF;
import com.max.highriskcpf.repositories.HighRiskCpfRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class HighRiskCpfServiceTest {


    @Mock
    private HighRiskCpfRepository repository;

    @InjectMocks
    private HighRiskCpfService service;

    @Test
    public void mustInsertANewCpfInDB(){
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").createdAt(Calendar.getInstance()).build();

        when(repository.save(Mockito.any(HighRiskCPF.class)))
                .thenReturn(cpf);

        HighRiskCPF savedCPF = service.insert(cpf);

        Assertions.assertThat(savedCPF).isNotNull();
        Assertions.assertThat(savedCPF).isEqualTo(cpf);
    }

    @Test
    public void mustReturnAllCPFsInDataBase(){
        HighRiskCPF cpf = Mockito.mock(HighRiskCPF.class);

        when(repository.findAll()).thenReturn(List.of(cpf));

        List<HighRiskCPF> cpfs = service.findAllFraudCPFs();

        Assertions.assertThat(cpfs).isNotNull();
        Assertions.assertThat(cpfs).isNotEmpty();

    }

    @Test
    public void mustCheckIfTheGivenCpfIsInDB(){
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").createdAt(Calendar.getInstance()).build();

        when(repository.findCPFByCpfEquals(cpf.getCpf())).thenReturn(cpf);

        HighRiskCPF cpfInDB = service.checkIfCpfIsInDB(cpf.getCpf());

        Assertions.assertThat(cpfInDB).isNotNull();
        Assertions.assertThat(cpfInDB.getCpf()).isEqualTo(cpf.getCpf());
    }

    @Test
    public void mustDeleteTheGivenCpfFromDB(){

        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").createdAt(Calendar.getInstance()).build();

        when(repository.findCPFByCpfEquals(cpf.getCpf())).thenReturn(cpf);

        HighRiskCPF cpfInDB = service.checkIfCpfIsInDB(cpf.getCpf());

        assertAll(() -> service.removeCpfFromDbByCpfValue(cpfInDB.getCpf()));
    }

    @Test
    public void mustFindAndValidateTheGivenCPF(){
        HighRiskCPF cpf = HighRiskCPF.builder().cpf("61548114030").createdAt(Calendar.getInstance()).build();

        when(repository.findCPFByCpfEquals(cpf.getCpf())).thenReturn(cpf);

        HighRiskCPF result = service.findCpfWithValidation(cpf.getCpf());

        Assertions.assertThat(cpf).isEqualTo(result);

    }

}
