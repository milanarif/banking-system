package org.bank.bankingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerEntity {

    @Id
    Long socialSecurity;

    String name;

    @ManyToOne
    private BankEntity bank;

    @OneToMany(mappedBy = "customer")
   private List<AccountEntity> accounts = new ArrayList<>();

    public CustomerEntity(Long socialSecurity, String name) {
        this.socialSecurity = socialSecurity;
        this.name = name;
    }

    public CustomerEntity() {
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

    public BankEntity getBank() {
        return bank;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }
}
