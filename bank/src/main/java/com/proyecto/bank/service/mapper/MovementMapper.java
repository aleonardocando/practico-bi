package com.proyecto.bank.service.mapper;

import com.proyecto.bank.controller.request.MovementRequest;
import com.proyecto.bank.persistence.entity.Movement;
import com.proyecto.bank.service.dto.MovementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    Movement movementDTOToMovement(MovementDTO movementDTO);

    MovementDTO movementToMovementDTO(Movement movement);

    Movement movementRequestToMovement(MovementRequest movementRequest);
}
