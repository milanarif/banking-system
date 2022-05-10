package org.bank.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long accountNumber;

    Long funds;


    @OneToOne
    @JoinTable(name = "account_user",
            joinColumns = {@JoinColumn(name = "account_number", referencedColumnName = "accountNumber")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private UserEntity user;

    @OneToMany(mappedBy = "account")
    private List<TransferEntity> transactions = new ArrayList<>();

    @OneToMany (mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<LoanEntity> loans = new ArrayList<>();

    public AccountEntity(Long funds) {
        this.funds = funds;
    }

    public AccountEntity() {
    }

    public void addTransaction(TransferEntity transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

    public void addLoan(LoanEntity loan){
        loans.add(loan);
        loan.setAccount(this);
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonIgnore
    public UserEntity getUser() {
        return user;
    }

    public Long getFunds() {
        return funds;
    }

    public void setFunds(Long funds) {
        this.funds = funds;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<TransferEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransferEntity> transactions) {
        this.transactions = transactions;
    }
}


