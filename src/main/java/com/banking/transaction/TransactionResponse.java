package com.banking.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        TransactionType type,
        String direction,
        Long relatedAccountId,
        String description,
        LocalDateTime timestamp,
        Long accountId
) {
}
