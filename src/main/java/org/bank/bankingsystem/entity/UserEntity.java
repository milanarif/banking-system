package org.bank.bankingsystem.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public abstract class UserEntity {
    @Id
    private Long socialSecurity;
    private String name;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roles;

    @ManyToOne
    private BankEntity bank;

    @OneToOne
    private AccountEntity account;

    public BankEntity getBank() {
        return bank;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public void setBank(BankEntity bank) {
        this.bank = bank;
    }
    public void addRole(RoleEntity role) {
        this.roles.add(role);
    }
    public void removeRole(RoleEntity role) {
        this.roles.remove(role);
    }
}
