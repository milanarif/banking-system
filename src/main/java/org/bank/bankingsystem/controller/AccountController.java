package org.bank.bankingsystem.controller;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {
    
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity account) {
        AccountEntity createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountEntity> findAccountById(@PathVariable Long id) {
        AccountEntity account = accountService.findAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<AccountEntity>> findAllAccounts() {
        Iterable<AccountEntity> accounts = accountService.findAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountEntity> updateAccount(@RequestBody AccountEntity account) {
        AccountEntity updatedAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AccountEntity> deposit(@PathVariable Long id, @RequestBody Long amount) {
        AccountEntity account = accountService.deposit(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AccountEntity> withdraw(@PathVariable Long id, @RequestBody Long amount) {
        AccountEntity account = accountService.withdraw(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
