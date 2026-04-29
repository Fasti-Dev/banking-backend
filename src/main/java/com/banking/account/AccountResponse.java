package com.banking.account;

import java.math.BigDecimal;

public record AccountResponse (
    Long id,
    String iban,
    BigDecimal balance,
    Long customerId
) {
}
