package org.bank.bankingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long accountNumber;

    Long funds;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private TransferEntity transaction;

    public AccountEntity(Long accountNumber, Long funds) {
        this.accountNumber = accountNumber;
        this.funds = funds;
    }

    public AccountEntity() {
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

    public TransferEntity getTransaction() {
        return transaction;
    }
}


