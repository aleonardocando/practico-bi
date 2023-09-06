package com.proyecto.bank.controller;

import com.proyecto.bank.service.dto.MovementDTO;
import com.proyecto.bank.controller.request.MovementRequest;
import com.proyecto.bank.service.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movements")
public class MovementController {

    @Autowired
    MovementService movementService;

    @GetMapping
    public ResponseEntity<List<MovementDTO>> listMovements(){
        return new ResponseEntity<>(movementService.listMovements(), HttpStatus.OK);
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<MovementDTO> findMovement(@PathVariable Integer movementId){
        return new ResponseEntity<>(movementService.findMovement(movementId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovementDTO> createMovement(@RequestBody MovementRequest movementRequest){
        return new ResponseEntity<>(movementService.createMovement(movementRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{movementId}")
    public ResponseEntity<MovementDTO> replaceMovement(@RequestBody MovementRequest movementRequest, @PathVariable Integer movementId){
        return new ResponseEntity<>(movementService.replaceMovement(movementRequest, movementId), HttpStatus.OK);
    }

    @PatchMapping("/{movementId}")
    public ResponseEntity<MovementDTO> updateMovement(@RequestBody Map<String, Object> datosMovimiento, @PathVariable Integer movementId){
        return new ResponseEntity<>(movementService.updateMovement(datosMovimiento, movementId), HttpStatus.OK);
    }

    @DeleteMapping("/{movementId}")
    public ResponseEntity<?> deleteMovement(@PathVariable Integer movementId){
        try{
            movementService.deleteMovement(movementId);
            return ResponseEntity.ok().build();
        }catch(EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    }


}
