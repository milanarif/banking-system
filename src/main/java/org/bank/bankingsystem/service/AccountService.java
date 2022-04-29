package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountEntity findAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new CustomException.NotFoundException("Account with account ID: " + accountId + " was not found in database."));
    }

    public AccountEntity createAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    public AccountEntity updateAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public AccountEntity deposit(Long accountId, Long amount) {
        AccountEntity account = findAccountById(accountId);
        account.setFunds(account.getFunds() + amount);
        updateAccount(account);
        return account;
    }

    public AccountEntity withdraw(Long accountId, Long amount) {
        AccountEntity account = findAccountById(accountId);
        if (account.getFunds() < amount) {
            throw new CustomException.InsufficientStorage("Insufficient funds");
        }
        account.setFunds(account.getFunds() - amount);
        updateAccount(account);
        return account;
    }

    public void transfer(Long fromAccountId, Long toAccountId, Long amount) {
        AccountEntity fromAccount = findAccountById(fromAccountId);
        AccountEntity toAccount = findAccountById(toAccountId);
        if (fromAccount.getFunds() < amount) {
            throw new CustomException.InsufficientStorage("Insufficient funds");
        }
        fromAccount.setFunds(fromAccount.getFunds() - amount);
        toAccount.setFunds(toAccount.getFunds() + amount);
        updateAccount(fromAccount);
        updateAccount(toAccount);
    }

    public Iterable<AccountEntity> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Long getBalance(Long accountId) {
        return findAccountById(accountId).getFunds();
    }
}
