package org.bank.bankingsystem.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(mappedBy = "bank")
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "bank")
    private List<Teller> tellers = new ArrayList<>();

    @OneToMany(mappedBy = "bank")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToOne
    private BankDirector bankDirector;


    public Bank(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bank() {
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Teller> getTellers() {
        return tellers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public BankDirector getBankDirector() {
        return bankDirector;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setTellers(List<Teller> tellers) {
        this.tellers = tellers;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setBankDirector(BankDirector bankDirector) {
        this.bankDirector = bankDirector;
    }
}
