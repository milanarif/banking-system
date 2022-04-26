package org.bank.bankingsystem.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class UserEntity {
    @Id
    private int socialSecurity;
    private String name;
    private Collection<RoleEntity> roles;

    @OneToOne
    private BankEntity bank;

    @OneToOne
    private AccountEntity account;


    public BankEntity getBank() {
        return bank;
    }
    public Collection<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
    public AccountEntity getAccount() {
        return account;
    }
    public void setAccount(AccountEntity account) {
        this.account = account;
    }
    public int getSocialSecurity() {
        return socialSecurity;
    }
    public void setSocialSecurity(int socialSecurity) {
        this.socialSecurity = socialSecurity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBank(BankEntity bank) {
        this.bank = bank;
    }
}
