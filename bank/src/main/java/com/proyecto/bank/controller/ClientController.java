package com.proyecto.bank.controller;

import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.controller.request.ClientRequest;
import com.proyecto.bank.service.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;


    @GetMapping
    public ResponseEntity<List<ClientDTO>> listClients(){
        return new ResponseEntity<>(clientService.listClients(), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> findClient(@PathVariable Integer clientId){
        return new ResponseEntity<>(clientService.findClient(clientId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientRequest clientRequest){
        return new ResponseEntity<>(clientService.createClient(clientRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> replaceClient(@RequestBody ClientRequest clientRequest, @PathVariable Integer clientId){
        return new ResponseEntity<>(clientService.replaceClient(clientRequest, clientId), HttpStatus.OK);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody Map<String, Object> clientData, @PathVariable Integer clientId){
        return new ResponseEntity<>(clientService.updateClient(clientData, clientId), HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId){
        try{
            clientService.deleteClient(clientId);
            return ResponseEntity.ok().build();
        }catch(EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    }


}
