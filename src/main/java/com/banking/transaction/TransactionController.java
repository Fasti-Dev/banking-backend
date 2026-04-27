package com.banking.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsByAccount(
            @PathVariable Long accountId
    ) {
        return transactionService.getTransactionsByAccount(accountId);
    }
}
