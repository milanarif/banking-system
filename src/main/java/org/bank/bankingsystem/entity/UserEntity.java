package org.bank.bankingsystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToOne
    private BankEntity bank;

    @OneToOne
    private AccountEntity account;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public Set<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
    public AccountEntity getAccount() {
        return account;
    }
    public void setAccount(AccountEntity account) {
        this.account = account;
    }
    public Long getId() {
        return id;
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
        roles.add(role);
        role.getUsers().add(this);
    }
    public void removeRole(RoleEntity role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }
}
