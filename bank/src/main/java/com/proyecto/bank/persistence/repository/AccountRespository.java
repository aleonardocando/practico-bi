package com.proyecto.bank.persistence.repository;

import com.proyecto.bank.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRespository extends JpaRepository<Account, Integer> {
    public Optional<Account> findByNumber(String numeroCuenta);
}
