package com.proyecto.bank.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 9, nullable = false)
    private String code;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 20, nullable = false)
    private String gender;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 10, nullable = false, unique = true)
    private String identification;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 15, nullable = false)
    private String phone;


    public Person(String code, String firstName, String lastName, String gender, Integer age, String identification, String address, String phone) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phone = phone;
    }
}
