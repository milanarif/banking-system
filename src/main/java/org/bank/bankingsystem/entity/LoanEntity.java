package org.bank.bankingsystem.entity;

import javax.persistence.*;

@Entity
public class LoanEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long loanId;

    Long loan;

    String name;

    float interest;

    @ManyToOne
    private AccountEntity account;


    public LoanEntity(Long loan, String name, float interest) {
        this.loan = loan;
        this.name = name;
        this.interest = interest;
    }

    public LoanEntity() {
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getLoan() {
        return loan;
    }

    public void setLoan(Long loan) {
        this.loan = loan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }
}
