package com.max.highriskcpf.controllers;

import com.max.highriskcpf.dto.HighRiskCpfDTO;
import com.max.highriskcpf.entities.ErrorMessage;
import com.max.highriskcpf.entities.HighRiskCPF;
import com.max.highriskcpf.exceptions.InvalidCpfException;
import com.max.highriskcpf.exceptions.NotFoundCpfException;
import com.max.highriskcpf.exceptions.RestResponseEntityExceptionHandler;
import com.max.highriskcpf.services.HighRiskCpfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    @Operation(description = "Must return all the CPFs in database, if there is none then " +
            "must return empty array")
    @ApiResponse(responseCode = "200", description = "All CPFs or empty array returned",
            content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "CPFs list", value = "[\"cpf1\", \"cpf2\", \"cpf3\"]"),
                            @ExampleObject(name = "Empty list", value = "[]")
                    })
            })
    @GetMapping(value = "/cpf")
    public ResponseEntity<List<HighRiskCpfDTO>> findAllFraudCPFs() {
        List<HighRiskCPF> cpfs = service.findAllFraudCPFs();
        List<HighRiskCpfDTO> cpfDTOS = cpfs.stream().map(cpf -> new HighRiskCpfDTO(cpf)).toList();
        return ResponseEntity.ok().body(cpfDTOS);
    }

    @Operation(description = "Must verify if the given CPF is in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CPF object returned",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(name = "CPF",
                                            value = "\"{\\\"cpf\\\":\\\"12345678909\\\",\\\"createdAt\\\":\\\"2023-03-27T02:41:56\\\"}\"")
                            })
                    }),
            @ApiResponse(responseCode = "400", description = "CPF provided is invalid, InvalidCpfException returned"
                    , content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "InvalidCpfException",
                                    value = "{\"type\":\"InvalidCpfException\",\"message\":\"CPFisnotvalid.\"}")
                    })
            }),
            @ApiResponse(responseCode = "404", description = "CPF not found, NotFoundCpfException returned"
                    , content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "NotFoundCpfException",
                                    value = "{\"type\":\"NotFoundCpfException\",\"message\":\"Can'tfindthegivenCPFindatabank\"}")
                    })
            })})
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<HighRiskCpfDTO> checkIfCpfIsInFraudList(@PathVariable String cpf) {
        HighRiskCPF cpfInDB = service.checkIfCpfIsInDB(cpf);
        return ResponseEntity.ok(new HighRiskCpfDTO(cpfInDB));
    }

    @Operation(description = "Must insert a new CPF in database",
            requestBody = @RequestBody(content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "CPF", value = "\"{\\\"cpf\\\":\\\"615.481.140-30\\\"}\"")
            })))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provided CPF successfully created in database"),
            @ApiResponse(responseCode = "400", description = "CPF provided is invalid, InvalidCpfException returned"
                    , content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "InvalidCpfException",
                                    value = "{\"type\":\"InvalidCpfException\",\"message\":\"CPFisnotvalid.\"}")
                    })
            }),
            @ApiResponse(responseCode = "409", description = "Provided CPF is already on database, ExistsCpfException returned"
                    , content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "ExistsCpfException",
                                    value = "\"{\\\"type\\\":\\\"ExistsCpfException\\\",\\\"message\\\":\\\"CPFalreadyexistsindatabase\\\"}\"")
                    })
            })
    })
    @PostMapping("/cpf")
    public ResponseEntity<Void> insertCPFInDB(@RequestBody HighRiskCpfDTO cpfDTO) {
        HighRiskCPF cpf = service.fromCPFDTO(cpfDTO);
        service.insert(cpf);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Must remove the provided CPF from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Provided CPF removed from database"),
            @ApiResponse(responseCode = "400", description = "CPF provided is invalid, InvalidCpfException returned"
                    , content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "InvalidCpfException",
                                    value = "{\"type\":\"InvalidCpfException\",\"message\":\"CPFisnotvalid.\"}")
                    })
            }),
            @ApiResponse(responseCode = "404", description = "CPF not found, NotFoundCpfException returned"
                    , content = {
                    @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "NotFoundCpfException",
                                    value = "{\"type\":\"NotFoundCpfException\",\"message\":\"Can'tfindthegivenCPFindatabank\"}")
                    })
            })})
    @DeleteMapping(value = "/cpf/{cpf}")
    public ResponseEntity<Void> removeCpfFromFraudList(@PathVariable String cpf) {
        service.removeCpfFromDbByCpfValue(cpf);
        return ResponseEntity.noContent().build();
    }

}
