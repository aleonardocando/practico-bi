package com.proyecto.bank.persistence.repository;

import com.proyecto.bank.persistence.entity.Employee;
import com.proyecto.bank.persistence.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where e.code = :code ")
    public Employee findByCodeEmployee(String code);

    public Optional<Employee> findByCode(String code);
}
