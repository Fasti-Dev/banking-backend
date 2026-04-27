package com.banking.account;

import com.banking.customer.Customer;
import com.banking.customer.CustomerRepository;
import com.banking.transaction.Transaction;
import com.banking.transaction.TransactionRepository;
import com.banking.transaction.TransactionType;

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
                .orElseThrow();

        Account account = Account.builder()
                .iban(generateIban())
                .balance(BigDecimal.ZERO)
                .customer(customer)
                .build();

        return accountRepository.save(account);
    }

    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow();

        account.setBalance(account.getBalance().add(amount));

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .build();

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow();

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.WITHDRAWAL)
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private String generateIban() {
        return "DE" + UUID.randomUUID().toString().substring(0, 10);
    }
}
