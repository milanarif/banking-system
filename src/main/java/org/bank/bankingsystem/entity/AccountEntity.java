package org.bank.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long accountNumber;

    Long funds;


    @OneToOne
    private UserEntity user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<TransferEntity> transactions = new ArrayList<>();

    public AccountEntity(Long funds) {
        this.funds = funds;
    }
    public AccountEntity() {
    }
    public void addTransaction(TransferEntity transaction){
        transactions.add(transaction);
       transaction.setAccount(this);
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    @JsonIgnore
    public UserEntity getUser() {
        return user;
    }

    public Long getFunds() {
        return funds;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
    public List<TransferEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransferEntity> transactions) {
        this.transactions = transactions;
    }
}


