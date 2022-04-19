package org.bank.bankingsystem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BankDirector {

    @Id
    Long socailSecurity;

    String name;

    @OneToOne
    private Bank bank;

    public BankDirector(Long socailSecurity, String name) {
        this.socailSecurity = socailSecurity;
        this.name = name;
    }

    public BankDirector() {
    }

    public Long getSocailSecurity() {
        return socailSecurity;
    }

    public void setSocailSecurity(Long socailSecurity) {
        this.socailSecurity = socailSecurity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
