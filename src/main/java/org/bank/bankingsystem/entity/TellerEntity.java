package org.bank.bankingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TellerEntity {

    @Id
    Long socialSecurity;

    String name;

    @ManyToOne
    private BankEntity bank;

    public TellerEntity(Long socialSecurity, String name) {
        this.socialSecurity = socialSecurity;
        this.name = name;
    }

    public TellerEntity() {
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
