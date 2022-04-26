package org.bank.bankingsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BankDirectorEntity {

    @Id
    Long socailSecurity;

    String name;

    @OneToOne
    private BankEntity bank;

    public BankDirectorEntity(Long socailSecurity, String name) {
        this.socailSecurity = socailSecurity;
        this.name = name;
    }

    public BankDirectorEntity() {
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
