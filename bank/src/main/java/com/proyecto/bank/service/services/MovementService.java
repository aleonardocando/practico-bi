package com.proyecto.bank.service.services;

import com.proyecto.bank.service.dto.MovementDTO;
import com.proyecto.bank.controller.request.MovementRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MovementService {
    MovementDTO createMovement(MovementRequest movementRequest);

    List<MovementDTO> listMovements();

    MovementDTO findMovement(Integer movementId);

    MovementDTO replaceMovement(MovementRequest movementRequest, Integer movementId);

    MovementDTO updateMovement(Map<String, Object> movementData, Integer movementId);

    void deleteMovement(Integer movementId);

}
