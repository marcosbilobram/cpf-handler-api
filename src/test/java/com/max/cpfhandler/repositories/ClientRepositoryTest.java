package com.max.cpfhandler.repositories;

import com.max.cpfhandler.entities.CPF;
import com.max.cpfhandler.entities.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void mustReturnAllClientsInDB() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();
        Client client2 = Client.builder().name("Marcos").cpf(CPF.builder().cpf("52982439085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();

        clientRepository.save(client);
        clientRepository.save(client2);
        clientRepository.flush();

        List<Client> clientList = clientRepository.findAll();

        Assertions.assertThat(clientList).isNotNull();
        Assertions.assertThat(clientList).isNotEmpty();
        Assertions.assertThat(clientList.size()).isGreaterThan(1);

    }

    @Test
    public void mustReturnAnUniqueClientWithTheGivenId() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();
        Client client2 = Client.builder().name("Marcos").cpf(CPF.builder().cpf("52982439085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();

        clientRepository.save(client);
        clientRepository.save(client2);
        clientRepository.flush();

        Client clientReturned = clientRepository.findById(1L).get();

        Assertions.assertThat(clientReturned).isNotNull();
        Assertions.assertThat(client.getId()).isEqualTo(clientReturned.getId());

    }

    @Test
    public void mustSaveANewClientWithAValidCPFInDB() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();

        Client savedClient = clientRepository.save(client);
        clientRepository.flush();

        Assertions.assertThat(clientRepository.findAll()).isNotEmpty();
        Assertions.assertThat(savedClient.getId()).isGreaterThan(0);

    }

    @Test
    public void mustUpdateAClientWithTheGivenId() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();

        clientRepository.save(client);
        clientRepository.flush();

        String newName = "Marcos H.";

        Client clientToUpdate = clientRepository.findById(1L).get();
        clientToUpdate.setName(newName);

        Client updatedClient = clientRepository.save(clientToUpdate);
        clientRepository.flush();

        Assertions.assertThat(clientToUpdate).isNotNull();
        Assertions.assertThat(updatedClient).isNotNull();
        Assertions.assertThat(updatedClient.getName()).isNotNull();
        Assertions.assertThat(updatedClient.getName()).isEqualTo(newName);
        Assertions.assertThat(client.getId()).isEqualTo(clientToUpdate.getId()).isEqualTo(updatedClient.getId());

    }

    @Test
    public void mustDeleteAClientWithTheGivenId() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();

        clientRepository.save(client);
        clientRepository.flush();

        String newName = "Marcos H.";

        clientRepository.deleteById(client.getId());
        Optional<Client> clientReturned = clientRepository.findById(client.getId());
        clientRepository.flush();

        Assertions.assertThat(clientReturned).isEmpty();

    }

    @Test
    public void mustReturnTheSpecifClientThatContainsTheValidGivenCPF() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(false).build()).build();

        clientRepository.save(client);
        clientRepository.flush();

        Client savedClient = clientRepository.findClientThatEqualsCpfValue(client.getCpf().getCpf());

        Assertions.assertThat(savedClient).isNotNull();
        Assertions.assertThat(client.getId()).isEqualTo(savedClient.getId());
        Assertions.assertThat(client.getCpf().getCpf()).isEqualTo(savedClient.getCpf().getCpf());

    }

    @Test
    public void mustReturnAllCpfsAreSavedAsFraudPossible() throws Exception {
        Client client = Client.builder().name("Marcos").cpf(CPF.builder().cpf("15277794085")
                .createdAt(Calendar.getInstance()).canBeaFraud(true).build()).build();

        Client client2 = Client.builder().name("Marcos").cpf(CPF.builder().cpf("52982439085")
                .createdAt(Calendar.getInstance()).canBeaFraud(true).build()).build();

        clientRepository.save(client);
        clientRepository.save(client2);
        clientRepository.flush();

        List<CPF> fraudulentCPFs = clientRepository.findAllFraudCPFs();

        Assertions.assertThat(fraudulentCPFs).isNotNull();
        Assertions.assertThat(fraudulentCPFs).isNotEmpty();
        Assertions.assertThat(fraudulentCPFs.size()).isGreaterThan(1);
        Assertions.assertThat(fraudulentCPFs.get(0).getCpf()).isEqualTo(client.getCpf().getCpf());
        Assertions.assertThat(fraudulentCPFs.get(1).getCpf()).isEqualTo(client2.getCpf().getCpf());

    }

}
