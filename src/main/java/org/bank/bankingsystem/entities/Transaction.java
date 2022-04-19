package org.bank.bankingsystem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transaction {

    @Id
    Long transactionID;

    Long funds;

    String date;

    @OneToMany(mappedBy = "transaction")
    private List<Account> accounts = new ArrayList<>();

    @ManyToOne
    private Bank bank;


    public Transaction(Long transactionID, Long funds, String date) {
        this.transactionID = transactionID;
        this.funds = funds;
        this.date = date;
    }

    public Transaction() {
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getFunds() {
        return funds;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
