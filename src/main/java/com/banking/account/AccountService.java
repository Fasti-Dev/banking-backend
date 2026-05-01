package com.banking.account;

import com.banking.customer.Customer;
import com.banking.customer.CustomerRepository;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionDirection;
import com.banking.transaction.TransactionRepository;
import com.banking.transaction.TransactionType;
import com.banking.common.exception.BankingException;
import com.banking.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public Account createAccount(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Account account = Account.builder()
                .iban(generateIban())
                .balance(BigDecimal.ZERO)
                .customer(customer)
                .build();

        return accountRepository.save(account);
    }

    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBalance(account.getBalance().add(amount));

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .direction(TransactionDirection.IN)
                .relatedAccountId(null)
                .description("Deposit to account " + account.getId())
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new BankingException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.WITHDRAWAL)
                .direction(TransactionDirection.OUT)
                .relatedAccountId(null)
                .description("Withdraw from account " + account.getId())
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account transfer(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        Account sourceAccount = accountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Source account not found"));

        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Target account not found"));

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new BankingException("Insufficient balance");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));

        Transaction sourceTransaction = Transaction.builder()
                .account(sourceAccount)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .direction(TransactionDirection.OUT)
                .relatedAccountId(targetAccount.getId())
                .description("Transfer to account " + targetAccount.getId())
                .timestamp(LocalDateTime.now())
                .build();

        Transaction targetTransaction = Transaction.builder()
                .account(targetAccount)
                .amount(amount)
                .type(TransactionType.TRANSFER)
                .direction(TransactionDirection.IN)
                .relatedAccountId(sourceAccount.getId())
                .description("Transfer from account " + sourceAccount.getId())
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(sourceTransaction);
        transactionRepository.save(targetTransaction);

        accountRepository.save(targetAccount);
        return accountRepository.save(sourceAccount);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private String generateIban() {
        return "DE" + UUID.randomUUID().toString().substring(0, 10);
    }
}