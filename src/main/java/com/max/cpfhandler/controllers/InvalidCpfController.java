package com.max.cpfhandler.controllers;

import com.max.cpfhandler.dto.InvalidCpfDTO;
import com.max.cpfhandler.entities.InvalidCPF;
import com.max.cpfhandler.services.InvalidCPFService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cpf")
public class InvalidCpfController {

    @Autowired
    InvalidCPFService service;

    @GetMapping
    public ResponseEntity<List<InvalidCpfDTO>> findAll(){
        List<InvalidCPF> cpfs = service.findAll();
        List<InvalidCpfDTO> cpfDTOS = cpfs.stream().map(ad -> new InvalidCpfDTO(ad)).toList();
        return ResponseEntity.ok().body(cpfDTOS);
    }

    @GetMapping(value = "/{cpf_id}")
    public ResponseEntity<InvalidCpfDTO> findById(@PathVariable Long id){
        InvalidCPF cpf = service.findById(id);
        return ResponseEntity.ok().body(new InvalidCpfDTO(cpf));
    }

    @GetMapping(value = "/{cpf_id}")
    public ResponseEntity<InvalidCpfDTO> findByCpfValue(@PathVariable String cpfValue){
        InvalidCPF cpf = service.findByCpfValue(cpfValue);
        return ResponseEntity.ok().body(new InvalidCpfDTO(cpf));
    }



}
