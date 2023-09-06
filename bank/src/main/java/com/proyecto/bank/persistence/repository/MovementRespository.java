package com.proyecto.bank.persistence.repository;

import com.proyecto.bank.persistence.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRespository extends JpaRepository<Movement, Integer> {

    @Query("select m from Movement m where m.date = :movementDate and m.account = :accountId and m.movementType = :movementType ")
    public Optional<List<Movement>> findByIdCuentaAndFecha(int accountId, Date movementDate, String movementType);

}
