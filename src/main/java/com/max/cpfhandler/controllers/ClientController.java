package com.max.cpfhandler.controllers;

import com.max.cpfhandler.dto.ClientDTO;
import com.max.cpfhandler.dto.CpfDTO;
import com.max.cpfhandler.entities.CPF;
import com.max.cpfhandler.entities.Client;
import com.max.cpfhandler.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/anti-fraud")
public class ClientController {

    @Autowired
    ClientService service;

    @PostMapping("/cpf")
    public ResponseEntity<Void> insertClientInDB(@RequestBody ClientDTO clientDTO) {
        Client client = service.fromClientDTO(clientDTO);
        client = service.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<CpfDTO> checkIfCpfIsSavedAsFraud(@PathVariable String cpf){
        CPF cpfInDB = service.checkIfCpfIsSavedAsFraud(cpf);
        return ResponseEntity.ok(new CpfDTO(cpfInDB));
    }

    @DeleteMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Void> removeCpfFromFraudList(@PathVariable String cpf){
        service.removeCPF(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/cpf")
    public ResponseEntity<List<CpfDTO>> findAllFraudCPFs(){
        List<CPF> cpfs = service.findAllFraudCPFs();
        List<CpfDTO> cpfDTOS = cpfs.stream().map(cpf -> new CpfDTO(cpf)).toList();
        return ResponseEntity.ok().body(cpfDTOS);
    }

    /*@GetMapping
    public ResponseEntity<List<ClientDTO>> findAllClients(){
        List<Client> clients = service.findAll();
        List<ClientDTO> clientsDTOS = clients.stream().map(ad -> new ClientDTO(ad)).toList();
        return ResponseEntity.ok().body(clientsDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id){
        Client client = service.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(client));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable Long id){
        Client client = service.fromClientDTO(clientDTO);
        client.setId(id);
        client = service.update(client);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/cpf")
    public ResponseEntity<Void> addCPFInFraudList(@RequestBody CpfDTO cpfDTO){
        //CPF cpf = service.fromCPFDTO(cpfDTO);
        service.cpfCanBeAFraud(cpfDTO.getCpf());
        return ResponseEntity.noContent().build();
    }
    */


}
