package org.bank.bankingsystem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    Long socialSecurity;

    String name;

    @ManyToOne
    private Bank bank;

    @OneToMany(mappedBy = "customer")
   private List<Account> accounts = new ArrayList<>();

    public Customer(Long socialSecurity, String name) {
        this.socialSecurity = socialSecurity;
        this.name = name;
    }

    public Customer() {
    }

    public Long getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Long socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bank getBank() {
        return bank;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
