package org.bank.bankingsystem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account {

    @Id
    Long accountNumber;

    Long funds;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private  Transaction transaction;

    public Account(Long accountNumber, Long funds) {
        this.accountNumber = accountNumber;
        this.funds = funds;
    }

    public Account() {
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

    public Customer getCustomer() {
        return customer;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}


