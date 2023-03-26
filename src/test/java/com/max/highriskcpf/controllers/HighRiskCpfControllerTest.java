package com.max.highriskcpf.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.highriskcpf.dto.HighRiskCpfDTO;
import com.max.highriskcpf.entities.HighRiskCPF;
import com.max.highriskcpf.exceptions.InvalidCpfException;
import com.max.highriskcpf.exceptions.NotFoundCpfException;
import com.max.highriskcpf.services.HighRiskCpfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void setup() {
        highRiskCPF = HighRiskCPF.builder().id(1L).cpf("674.350.660-52").createdAt(Calendar.getInstance()).build();
        highRiskCpfDTO = HighRiskCpfDTO.builder().cpf("674.350.660-52").createdAt(Calendar.getInstance()).build();
    }

    @Test
    public void mustInsertNewCpfAndReturnCreatedInPostMethod() throws Exception {

        when(service.insert(ArgumentMatchers.any()))
                .thenReturn(highRiskCPF);

        mockMvc.perform(post("/high-risk-cpf/cpf")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(highRiskCpfDTO)))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void mustThrowInvalidCpfExceptionWhenInvalidCpfIsGivenInPostMethod() throws Exception {
        HighRiskCpfDTO cpfDTO = highRiskCpfDTO;
        cpfDTO.setCpf("123");

        HighRiskCPF cpf = highRiskCPF;
        cpf.setCpf("14");

        when(service.insert(ArgumentMatchers.any())).thenThrow(InvalidCpfException.class);

        this.mockMvc.perform(post("/high-risk-cpf/cpf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cpfDTO)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void mustReturnTheGivenCpfInDbAfterGetRequest() throws Exception {

        when(service.checkIfCpfIsInDB(highRiskCPF.getCpf())).thenReturn(highRiskCPF);

        this.mockMvc.perform(get("/high-risk-cpf/cpf/{cpf}", highRiskCPF.getCpf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.cpf").value(highRiskCPF.getCpf()));

    }

    @Test
    public void mustReturnInvalidCPfExceptionWhenCpfIsGivenInGetRequest() throws Exception {

        String invalidCPF = "1684684684";
        when(service.checkIfCpfIsInDB(invalidCPF)).thenThrow(InvalidCpfException.class);

        this.mockMvc.perform(get("/high-risk-cpf/cpf/{cpf}", invalidCPF)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.type").value("InvalidCpfException"))
                .andExpect(jsonPath("$.message").value("CPF is not valid."));

    }

    @Test
    public void mustReturnNotFoundCpfExceptionInTheGetRequestResponse() throws Exception {
        String cpf = "770.030.560-97";
        when(service.checkIfCpfIsInDB(cpf)).thenThrow(NotFoundCpfException.class);

        this.mockMvc.perform(get("/high-risk-cpf/cpf/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(jsonPath("$.type").value("NotFoundCpfException"));
    }

    @Test
    public void mustReturnAllCPFsInDataBank() throws Exception {

        when(service.findAllFraudCPFs()).thenReturn(List.of(highRiskCPF));

        this.mockMvc.perform(get("/high-risk-cpf/cpf")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void mustDeleteTheGivenCpfFromDataBankAndReturnNoContent() throws Exception {
        String cpf = highRiskCPF.getCpf();
       doNothing().when(service).removeCpfFromDbByCpfValue(cpf);

       this.mockMvc.perform(delete("/high-risk-cpf/cpf/{cpf}", cpf)
               .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isNoContent());
    }

    @Test
    public void mustReturnInvalidCpfExceptionInDeleteRequest() throws Exception {
        String cpf = "50431225023";
        doThrow(InvalidCpfException.class).when(service).removeCpfFromDbByCpfValue(cpf);

        this.mockMvc.perform(delete("/high-risk-cpf/cpf/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("InvalidCpfException"))
                .andExpect(jsonPath("$.message").value("CPF is not valid."));
    }

    @Test
    public void mustReturnNotFoundCpfExceptionInDeleteRequest() throws Exception {
        String cpf = "815.740.600-58";
        doThrow(NotFoundCpfException.class).when(service).removeCpfFromDbByCpfValue(cpf);

        this.mockMvc.perform(delete("/high-risk-cpf/cpf/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.type").value("NotFoundCpfException"))
                .andExpect(jsonPath("$.message").value("Can't find the given CPF in data bank"));
    }
}
