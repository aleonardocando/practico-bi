package com.proyecto.bank.service.mapper;

import com.proyecto.bank.controller.request.ClientRequest;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.persistence.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client clientDTOToClient(ClientDTO clientDTO);

    ClientDTO clientToClientDTO(Client client);

    Client clientRequestToClient(ClientRequest clientRequest);
}
