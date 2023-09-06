package com.proyecto.bank.persistence.repository;

import com.proyecto.bank.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Optional<Client> findByIdentification(String clientId);

    public Optional<Client> findByCode(String code);
}
