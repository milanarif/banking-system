package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.BankEntity;
import org.bank.bankingsystem.entity.TransferEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.bank.bankingsystem.repository.TransactionRepository;
import org.bank.bankingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountEntity findAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new CustomException.NotFoundException("Account with account ID: " + accountId + " was not found in database."));
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

    public List<AccountEntity> transfer(Long fromAccountId, Long toAccountId, Long amount) {
        AccountEntity fromAccount = findAccountById(fromAccountId);
        AccountEntity toAccount = findAccountById(toAccountId);
        if (fromAccount.getFunds() < amount) {
            throw new CustomException.InsufficientStorage("Insufficient funds");
        }
        fromAccount.setFunds(fromAccount.getFunds() - amount);
        toAccount.setFunds(toAccount.getFunds() + amount);
        updateAccount(fromAccount);
        updateAccount(toAccount);

        BankEntity bank = new BankEntity("Coolbank");

        TransferEntity transaction = new TransferEntity(amount, fromAccount, toAccount, bank);

        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
        transactionRepository.save(transaction);

        List<AccountEntity> accounts = new ArrayList<>();

        accounts.add(fromAccount);
        accounts.add(toAccount);
        return accounts;
    }

    public Iterable<AccountEntity> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Long getBalance(Long accountId) {
        return findAccountById(accountId).getFunds();
    }
}
