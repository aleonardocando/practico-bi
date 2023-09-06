package com.proyecto.bank.persistence.entity;

import com.proyecto.bank.utils.enums.AccountTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10, nullable = false, unique = true)
    private String number;

    @Column(length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AccountTypeEnum type;

    @Column(nullable = false)
    private int balance;

    @Column(nullable = false)
    private Boolean state;

    @OneToMany(targetEntity = Movement.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private List<Movement> movements;

    @Column(name = "client_id")
    private int client;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    public Account(String number, int balance, AccountTypeEnum type, int id) {
        this.number = number;
        this.balance = balance;
        this.type = type;
        this.client = id;
        this.state = Boolean.TRUE;
    }
}
