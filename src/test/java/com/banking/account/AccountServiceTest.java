package com.banking.account;

import com.banking.common.exception.BankingException;
import com.banking.common.exception.ResourceNotFoundException;
import com.banking.customer.Customer;
import com.banking.customer.CustomerRepository;
import com.banking.transaction.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);

    private final AccountService accountService =
            new AccountService(accountRepository, customerRepository, transactionRepository);

    @Test
    void deposit_shouldIncreaseBalance() {

        Account account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(100))
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.deposit(1L, BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(150), result.getBalance());
        verify(accountRepository).save(account);
    }

    @Test
    void withdraw_shouldDecreaseBalance() {

        Account account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(200))
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.withdraw(1L, BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(150), result.getBalance());
        verify(accountRepository).save(account);
    }

    @Test
    void withdraw_shouldThrowException_whenBalanceTooLow() {

        Account account = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(50))
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(BankingException.class, () ->
                accountService.withdraw(1L, BigDecimal.valueOf(100))
        );
    }

    @Test
    void transfer_shouldMoveMoneyBetweenAccounts() {

        Account source = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(200))
                .build();

        Account target = Account.builder()
                .id(2L)
                .balance(BigDecimal.valueOf(100))
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(target));

        when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArgument(0));

        accountService.transfer(1L, 2L, BigDecimal.valueOf(50));

        assertEquals(BigDecimal.valueOf(150), source.getBalance());
        assertEquals(BigDecimal.valueOf(150), target.getBalance());

        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    void transfer_shouldThrowException_whenBalanceTooLow() {

        Account source = Account.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(50))
                .build();

        Account target = Account.builder()
                .id(2L)
                .balance(BigDecimal.valueOf(100))
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(target));

        assertThrows(BankingException.class, () ->
                accountService.transfer(1L, 2L, BigDecimal.valueOf(100))
        );
    }
}
