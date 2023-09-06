package com.proyecto.bank.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Employee extends Person{
    @Column(length = 20, nullable = false)
    private String role;

    public Employee(String code, String firstName, String lastName, String gender, Integer age, String identification, String address, String phone, String role) {
        super(code, firstName, lastName, gender, age, identification, address, phone);
        this.role = role;
    }
}
