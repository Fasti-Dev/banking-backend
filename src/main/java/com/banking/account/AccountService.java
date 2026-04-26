package com.banking.account;

import com.banking.customer.Customer;
import com.banking.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

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

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private String generateIban() {
        return "DE" + UUID.randomUUID().toString().substring(0, 10);
    }
}
