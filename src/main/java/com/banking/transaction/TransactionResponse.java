package com.banking.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        TransactionType type,
        LocalDateTime timestamp,
        Long accountId
) {
}
