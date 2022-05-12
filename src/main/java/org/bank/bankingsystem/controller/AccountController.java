package org.bank.bankingsystem.controller;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.TransferEntity;
import org.bank.bankingsystem.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ArrayList> findAccountById(@PathVariable Long id) {
        ArrayList response = new ArrayList();
        AccountEntity account = accountService.findAccountById(id);
        ArrayList transactions = accountService.findAccountTransfers(id);
        response.add(account);
        response.add(transactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactions")
    public ResponseEntity<Iterable<TransferEntity>> getAllTransactions() {
        Iterable<TransferEntity> transactions = accountService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PutMapping("/transaction/{senderId}/{receiverId}")
    public ResponseEntity<TransferEntity> transaction(@PathVariable Long senderId,
                                                           @PathVariable Long receiverId,
                                                           @QueryParam("amount") Long amount) {
        TransferEntity transfer = accountService.transfer(senderId, receiverId, amount);
        return new ResponseEntity<>(transfer, HttpStatus.OK);
    }

    @PutMapping("deposit/{id}")
    public ResponseEntity<AccountEntity> deposit(@PathVariable Long id,
                                                  @QueryParam("amount") Long amount) {
        AccountEntity deposit = accountService.deposit(id, amount);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<AccountEntity> withdraw(@PathVariable Long id,
                                                 @QueryParam("amount") Long amount) {
        AccountEntity withdraw = accountService.withdraw(id, amount);
        return new ResponseEntity<>(withdraw, HttpStatus.OK);
    }
}
