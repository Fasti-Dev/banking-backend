package com.banking.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor

public class AccountController {

    private final AccountService accountService;

    @PostMapping("/customer/{customerId}")
    public Account createAccount(@PathVariable Long customerId) {
        return accountService.createAccount(customerId);
    }

    @PostMapping("/{accountId}/deposit")
    public Account deposit(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount
    ) {
        return accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public Account withdraw(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount
    ) {
        return accountService.withdraw(accountId, amount);
    }

    @PostMapping("/{sourceAccountId}/transfer/{targetAccountId}")
    public Account transfer(
            @PathVariable Long sourceAccountId,
            @PathVariable Long targetAccountId,
            @RequestParam BigDecimal amount
    ) {
        return accountService.transfer(sourceAccountId, targetAccountId, amount);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}