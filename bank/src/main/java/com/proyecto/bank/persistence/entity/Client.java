package com.proyecto.bank.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Client extends Person {

    @Column(length = 20, nullable = false)
    private String password;

    @Column
    private Boolean state;

    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private List<Account> accounts;

    public Client(String code, String firstName, String lastName, String gender, Integer age, String identification, String address, String phone, String password, Boolean state) {
        super(code, firstName, lastName, gender, age, identification, address, phone);
        this.password = password;
        this.state = state;
    }

}
