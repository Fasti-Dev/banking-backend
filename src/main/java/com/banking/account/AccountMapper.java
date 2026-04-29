package com.banking.account;

public class AccountMapper {

    public static AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getIban(),
                account.getBalance(),
                account.getCustomer().getId()
        );
    }
}
