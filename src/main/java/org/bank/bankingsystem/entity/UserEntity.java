package org.bank.bankingsystem.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
public abstract class UserEntity {
    @Id
    private Long socialSecurity;
    private String name;
    private String password;

    @ManyToOne
    private BankEntity bank;

    @OneToOne
    private AccountEntity account;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(Long socialSecurity, String name, String password, BankEntity bank, AccountEntity account, Collection<RoleEntity> roles) {
        this.socialSecurity = socialSecurity;
        this.name = name;
        this.password = password;
        this.bank = bank;
        this.account = account;
        this.roles = roles;
    }

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
