package com.max.cpfhandler.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.cpfhandler.dto.HighRiskCpfDTO;
import com.max.cpfhandler.entities.HighRiskCPF;
import com.max.cpfhandler.exceptions.InvalidCpfException;
import com.max.cpfhandler.repositories.HighRiskCpfRepository;
import com.max.cpfhandler.services.HighRiskCpfService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HighRiskCpfController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class HighRiskCpfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HighRiskCpfService service;

    @Autowired
    ObjectMapper objectMapper;

    private HighRiskCPF highRiskCPF;

    private HighRiskCpfDTO highRiskCpfDTO;

    @BeforeEach
    public void setup(){
        highRiskCPF = HighRiskCPF.builder().id(1L).cpf("674.350.660-52").createdAt(Calendar.getInstance()).build();
        highRiskCpfDTO = HighRiskCpfDTO.builder().cpf("674.350.660-52").createdAt(Calendar.getInstance()).build();
    }

    @Test
    public void mustInsertNewCpfAndReturnCreated() throws Exception{

        when(service.insert(ArgumentMatchers.any()))
                .thenReturn(highRiskCPF);

        mockMvc.perform(post("/high-risk-cpf/cpf")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(highRiskCpfDTO)))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void mustThrowInvalidCpfExceptionWhenInvalidCpfIsPassed() throws InvalidCpfException{

    }

}
