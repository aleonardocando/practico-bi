package com.proyecto.bank.service.services.impl;

import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.controller.request.ClientRequest;
import com.proyecto.bank.exceptions.ClientNotFoundException;
import com.proyecto.bank.service.mapper.ClientMapper;
import com.proyecto.bank.persistence.entity.Client;
import com.proyecto.bank.persistence.repository.ClientRepository;
import com.proyecto.bank.service.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientDTO createClient(ClientRequest clientRequest){
        return clientMapper.INSTANCE.clientToClientDTO(
                clientRepository.save(
                        clientMapper.INSTANCE.clientRequestToClient(clientRequest)));
    }

    public List<ClientDTO> listClients(){
        List<ClientDTO> clientsDTOList= new ArrayList<>();
        for (Client client : clientRepository.findAll()
             ) {
            clientsDTOList.add(clientMapper.INSTANCE.clientToClientDTO(client));
        }
        return clientsDTOList;
    }

    public ClientDTO findClient(Integer clientId){
        Optional<Client> clientResult = clientRepository.findById(clientId);
        if(clientResult.isPresent()){
            return clientMapper.INSTANCE.clientToClientDTO(clientResult.get());
        }else{
            throw new ClientNotFoundException(clientId);
        }
    }

    public ClientDTO replaceClient(ClientRequest clientRequest, Integer clientId){
        Optional<Client> clientResult = clientRepository.findById(clientId);
        if(clientResult.isPresent()){
            Client client = clientMapper.INSTANCE.clientRequestToClient(clientRequest);
            client.setId(clientResult.get().getId());
            return clientMapper.INSTANCE.clientToClientDTO(clientRepository.save(client));
        }else{
            throw new ClientNotFoundException(clientId);
        }
    }

    public ClientDTO updateClient(Map<String, Object> clientData, Integer clientId){
        Optional<Client> clientResult = Optional.ofNullable(clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId)));
        if(clientResult.isPresent()) {
            clientData.forEach((clave, valor) -> {
                Field campo = ReflectionUtils.findField(Client.class, clave);
                campo.setAccessible(true);
                ReflectionUtils.setField(campo, clientResult.get(), valor);
            });
            return clientMapper.INSTANCE.clientToClientDTO(clientRepository.save(clientResult.get()));
        }else{
            throw new ClientNotFoundException(clientId);
        }
    }

    public void deleteClient(Integer clientId) {
        if(clientRepository.findById(clientId).isPresent()) {
            clientRepository.deleteById(clientId);
        }else{
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public ClientDTO findClientByIdentification(String identificationClient) {
        Optional<Client> clientResult = clientRepository.findByIdentification(identificationClient);
        if(clientResult.isPresent()) {
            return clientMapper.INSTANCE.clientToClientDTO(clientResult.get());
        }else{
            throw new ClientNotFoundException(identificationClient);
        }
    }

    @Override
    public ClientDTO findClientByCode(String code) {
        Optional<Client> clientResult = clientRepository.findByCode(code);
        if(clientResult.isPresent()) {
            return clientMapper.INSTANCE.clientToClientDTO(clientResult.get());
        }else{
            throw new ClientNotFoundException(code);
        }
    }

}
