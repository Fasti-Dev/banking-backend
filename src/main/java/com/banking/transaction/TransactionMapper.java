package com.banking.transaction;

public class TransactionMapper {

    public static TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDirection(),
                transaction.getRelatedAccountId(),
                transaction.getDescription(),
                transaction.getTimestamp(),
                transaction.getAccount().getId()
        );
    }
}
