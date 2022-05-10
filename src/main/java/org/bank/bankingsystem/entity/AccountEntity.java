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
    private List<LoanEntity> loans = new ArrayList<>();

    public AccountEntity(Long funds) {
        this.funds = funds;
    }

    public AccountEntity() {
    }

    public void addLoan(LoanEntity loan) {
        loans.add(loan);
        loan.setAccount(this);
    }

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
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
}


