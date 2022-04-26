package org.bank.bankingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TransactionEntity {

    @Id
    Long transactionID;

    Long funds;

    String date;

    @OneToMany(mappedBy = "transaction")
    private List<AccountEntity> accounts = new ArrayList<>();

    @ManyToOne
    private BankEntity bank;


    public TransactionEntity(Long transactionID, Long funds, String date) {
        this.transactionID = transactionID;
        this.funds = funds;
        this.date = date;
    }

    public TransactionEntity() {
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

    public List<AccountEntity> getAccounts() {
        return accounts;
    }
}
