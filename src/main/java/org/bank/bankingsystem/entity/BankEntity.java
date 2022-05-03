package org.bank.bankingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "bank")
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "bank")
    private List<TransferEntity> transactions = new ArrayList<>();


    public BankEntity(String name) {
        this.name = name;
    }

    public BankEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public List<TransferEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransferEntity> transactions) {
        this.transactions = transactions;
    }
}
