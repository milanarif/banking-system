package org.bank.bankingsystem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Teller {

    @Id
    Long socialSecurity;

    String name;

    @ManyToOne
    private Bank bank;

    public Teller(Long socialSecurity, String name) {
        this.socialSecurity = socialSecurity;
        this.name = name;
    }

    public Teller() {
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
}
