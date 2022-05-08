package org.bank.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;


@Entity
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long transferId;

    Long amount;

    private LocalDate date;

    @OneToOne
    AccountEntity senderAccount;

    @OneToOne
    AccountEntity receiverAccount;

    @ManyToOne
    private AccountEntity account;


    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }


    public TransferEntity(Long amount, AccountEntity senderAccount, AccountEntity receiverAccount) {
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
    }

    public TransferEntity() {
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @JsonIgnore
    public AccountEntity getSenderAccount() {
        return senderAccount;
    }

    @JsonIgnore
    public AccountEntity getReceiverAccount() {
        return receiverAccount;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long setAmount() {
        return amount;
    }

    public void getAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }


    public void setSenderAccount(AccountEntity senderAccount) {
        this.senderAccount = senderAccount;
    }

    public void setReceiverAccount(AccountEntity receiverAccount) {
        this.receiverAccount = receiverAccount;
    }
}
