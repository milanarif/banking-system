package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.BankEntity;
import org.bank.bankingsystem.entity.TransferEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.bank.bankingsystem.repository.BankRepository;
import org.bank.bankingsystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;

    private final BankRepository bankRepository;

    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, BankRepository bankRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
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

    public TransferEntity transfer(Long fromAccountId, Long toAccountId, Long amount) {
        AccountEntity fromAccount = findAccountById(fromAccountId);
        AccountEntity toAccount = findAccountById(toAccountId);
        if (fromAccount.getFunds() < amount) {
            throw new CustomException.InsufficientStorage("Insufficient funds");
        }
        fromAccount.setFunds(fromAccount.getFunds() - amount);
        toAccount.setFunds(toAccount.getFunds() + amount);

        BankEntity bank = new BankEntity("Coolbank");
        bankRepository.save(bank);

        TransferEntity transaction = new TransferEntity(amount, fromAccount, toAccount, bank);
        transactionRepository.save(transaction);
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return transaction;
    }

    public Iterable<AccountEntity> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Long getBalance(Long accountId) {
        return findAccountById(accountId).getFunds();
    }
}
