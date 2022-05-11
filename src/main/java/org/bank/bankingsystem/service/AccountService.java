package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.*;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.bank.bankingsystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountEntity findAccountById(Long accountId) {
        AccountEntity findAccount = accountRepository.findById(accountId).orElseThrow(() -> new CustomException.NotFoundException("Account with account ID: " + accountId + " was not found in database."));
        return findAccount;
    }

    public ArrayList findAccountTransfers(Long accountId) {
        ArrayList transactions = transactionRepository.findTransferEntityBySenderAccountId(accountId);
        if (transactions == null) {
            transactions.add("No transaction history");
        }
        return transactions;
    }

    public AccountEntity createAccount(UserEntity user) {
        AccountEntity account = new AccountEntity();
        account.setFunds(0L);
        account.setUser(user);
        return accountRepository.save(account);
    }

    public AccountEntity updateAccount(AccountEntity account) {
        return accountRepository.save(account);
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

        TransferEntity transaction = new TransferEntity(amount, fromAccount.getAccountNumber(), toAccount.getAccountNumber());
        transactionRepository.save(transaction);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        System.out.println("Processed transaction " + transaction.getTransferId());
        return transaction;
    }

    public Iterable<TransferEntity> getAllTransactions(){
        Iterable<TransferEntity> transactions = transactionRepository.findAll();
        if(transactions.equals(null))
            throw new CustomException.NotFoundException("No transactions were found in database.");

        return transactions;
    }

    public Iterable<AccountEntity> findAllAccounts() {
        Iterable<AccountEntity> accounts = accountRepository.findAll();
        if(accounts.equals(null))
            throw  new CustomException.NotFoundException("No accounts were found in database.");
        return accounts;
    }
}
