package com.max.cpfhandler.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.cpfhandler.dto.ClientDTO;
import com.max.cpfhandler.dto.CpfDTO;
import com.max.cpfhandler.entities.CPF;
import com.max.cpfhandler.entities.Client;
import com.max.cpfhandler.repositories.ClientRepository;
import com.max.cpfhandler.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClientController clientController;

    @MockBean
    ClientRepository clientRepository;

    List<ClientDTO> clientsDTO;

    ClientDTO clientDTO;

    CpfDTO cpfDTO;

    List<Client> clients;

    Client client;

    CPF cpf;

    @BeforeEach
    public void setup(){
        //cpf = CpfBuilder.builder().cpf("152.777.940-85")
                //.createdAt(Calendar.getInstance()).canBeaFraud(false).build();

        //cpfDTO = CpfDTOBuilder
        //clientDTO = ClientDTOBuilder.builder().id(1L).name("Marcos")
                //.cpf().build();
        client = new Client();
        clientsDTO = new ArrayList<>();
        clients = new ArrayList<>();
    }

    @Test
    public void mustCreateANewClient() throws Exception{



    }

    @Test
    public void mustReturnAllClientsInDB_WhenMakeAGetRequest() throws Exception {

        Mockito.when(clientService.findAll()).thenReturn(clients);

        this.mockMvc.perform(get("/client"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    public void mustReturnTheClientThatContainsTheSpecificID() {

    }

}
