package com.max.highriskcpf.controllers;

import com.max.highriskcpf.dto.HighRiskCpfDTO;
import com.max.highriskcpf.entities.HighRiskCPF;
import com.max.highriskcpf.services.HighRiskCpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/high-risk-cpf")
public class HighRiskCpfController {

    @Autowired
    HighRiskCpfService service;

    @GetMapping(value = "/cpf")
    public ResponseEntity<List<HighRiskCpfDTO>> findAllFraudCPFs(){
        List<HighRiskCPF> cpfs = service.findAllFraudCPFs();
        List<HighRiskCpfDTO> cpfDTOS = cpfs.stream().map(cpf -> new HighRiskCpfDTO(cpf)).toList();
        return ResponseEntity.ok().body(cpfDTOS);
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<HighRiskCpfDTO> checkIfCpfIsInFraudList(@PathVariable String cpf){
        HighRiskCPF cpfInDB = service.checkIfCpfIsInDB(cpf);
        return ResponseEntity.ok(new HighRiskCpfDTO(cpfInDB));
    }

    @PostMapping("/cpf")
    public ResponseEntity<Void> insertCPFInDB(@RequestBody HighRiskCpfDTO cpfDTO) {
        HighRiskCPF cpf = service.fromCPFDTO(cpfDTO);
        service.insert(cpf);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Void> removeCpfFromFraudList(@PathVariable String cpf){
        service.removeCpfFromDbByCpfValue(cpf);
        return ResponseEntity.noContent().build();
    }

}
