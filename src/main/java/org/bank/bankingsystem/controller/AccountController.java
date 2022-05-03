package org.bank.bankingsystem.controller;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity account) {
        AccountEntity createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountEntity> findAccountById(@PathVariable Long id) {
        AccountEntity account = accountService.findAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Long id, @RequestBody AccountEntity account) {
        AccountEntity updatedAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @GetMapping("{id}/balance")
    public ResponseEntity<Long> getBalance(@PathVariable Long id) {
        Long balance = accountService.getBalance(id);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AccountEntity> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/transaction/{senderId}/{reciverId}")
    public ResponseEntity<List<AccountEntity>> transaction(@PathParam("senderId") Long senderId,
                                                           @PathParam("reciverId") Long reciverId,
                                                           @QueryParam("amount") Long amount  ) {
       List<AccountEntity> accounts = accountService.transfer(senderId, reciverId, amount);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
