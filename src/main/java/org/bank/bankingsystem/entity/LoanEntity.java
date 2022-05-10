package org.bank.bankingsystem.entity;

import javax.persistence.*;

@Entity
public class LoanEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long loanId;

    Long loan;

    Double interest;

    @ManyToOne
    private AccountEntity account;

    public LoanEntity(Long loan, Double interest) {
        this.loan = loan;
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

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }
}
