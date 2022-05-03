package org.bank.bankingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountNumber;

    Long funds;

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<TransferEntity> transactions = new ArrayList<>();

    public AccountEntity(Long accountNumber, Long funds) {
        this.accountNumber = accountNumber;
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

    public Long getFunds() {
        return funds;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public UserEntity getUser() {
        return user;
    }

    public List<TransferEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransferEntity> transactions) {
        this.transactions = transactions;
    }
}


