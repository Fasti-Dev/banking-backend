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
    public AccountResponse createAccount(@PathVariable Long customerId) {
        return AccountMapper.toResponse(
                accountService.createAccount(customerId)
        );
    }

    @PostMapping("/{accountId}/deposit")
    public AccountResponse deposit(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount
    ) {
        return AccountMapper.toResponse(
                accountService.deposit(accountId, amount)
        );
    }

    @PostMapping("/{accountId}/withdraw")
    public AccountResponse withdraw(
            @PathVariable Long accountId,
            @RequestParam BigDecimal amount
    ) {
        return AccountMapper.toResponse(
                accountService.withdraw(accountId, amount)
        );
    }

    @PostMapping("/{sourceAccountId}/transfer/{targetAccountId}")
    public AccountResponse transfer(
            @PathVariable Long sourceAccountId,
            @PathVariable Long targetAccountId,
            @RequestParam BigDecimal amount
    ) {
        return AccountMapper.toResponse(
                accountService.transfer(sourceAccountId, targetAccountId, amount)
        );
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts()
                .stream()
                .map(AccountMapper::toResponse)
                .toList();
    }
}