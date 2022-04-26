package org.bank.bankingsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(mappedBy = "bank")
    private List<CustomerEntity> customers = new ArrayList<>();

    @OneToMany(mappedBy = "bank")
    private List<TellerEntity> tellers = new ArrayList<>();

    @OneToMany(mappedBy = "bank")
    private List<TransactionEntity> transactions = new ArrayList<>();

    @OneToOne
    private BankDirectorEntity bankDirector;


    public BankEntity(Long id, String name) {
        this.id = id;
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

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public List<TellerEntity> getTellers() {
        return tellers;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public BankDirectorEntity getBankDirector() {
        return bankDirector;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }

    public void setTellers(List<TellerEntity> tellers) {
        this.tellers = tellers;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public void setBankDirector(BankDirectorEntity bankDirector) {
        this.bankDirector = bankDirector;
    }
}
