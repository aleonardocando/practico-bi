package com.proyecto.bank.service.services;

import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.controller.request.ClientRequest;

import java.util.List;
import java.util.Map;

public interface ClientService {
    ClientDTO createClient(ClientRequest clientRequest);

    List<ClientDTO> listClients();

    ClientDTO findClient(Integer clientId);

    ClientDTO replaceClient(ClientRequest clientRequest, Integer clientId);

    ClientDTO updateClient(Map<String, Object> clientData, Integer clientId);

    void deleteClient(Integer clientId);

    ClientDTO findClientByIdentification(String identificationClient);

    ClientDTO findClientByCode(String code);

}