package org.bank.bankingsystem.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;


@Entity
public class TransferEntity {

    @Id
    Long transferId;

    Long amount;

    private LocalDate date;

    @OneToOne
    AccountEntity senderAccount;

    @OneToOne
    AccountEntity receiverAccount;

    @ManyToOne
    private BankEntity receiverBank;

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }


    public TransferEntity(Long transferId, Long amount, AccountEntity senderAccount, AccountEntity receiverAccount, BankEntity receiverBank) {
        this.transferId = transferId;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.receiverBank = receiverBank;
    }

    public TransferEntity() {
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
}
