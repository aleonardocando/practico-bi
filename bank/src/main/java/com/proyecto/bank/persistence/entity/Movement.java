package com.proyecto.bank.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(length = 20, nullable = false)
    private String movement;

    @Column(length = 1, nullable = false)
    private String movementType;

    @Column(nullable = false)
    private int value;

    @Column(nullable = false)
    private int balance;
    @Column(name = "account_id")
    private int account;

    @Column(length = 20, nullable = false)
    private String activity;

    public Movement(int id, int balance, String movement, String movementType, int value, String activity) {
        this.account = id;
        this.date = new Date();
        this.balance = balance;
        this.movement = movement;
        this.movementType = movementType;
        this.value = value;
        this.activity= activity;
    }
}
