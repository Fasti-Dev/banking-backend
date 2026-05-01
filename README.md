# Banking Backend

A small banking backend built with **Java**, **Spring Boot**, **Maven** and **H2 Database**.

## About

This project is a learning and portfolio project that demonstrates a simple backend architecture for a banking system.

Includes clean REST API design, centralized exception handling, and DTO-based architecture with mapper classes.

The application currently supports:

- creating customers
- creating bank accounts
- depositing money
- withdrawing money
- transferring money between accounts
- viewing account balances
- viewing transaction history

---

## Tech Stack

- Java 21
- Spring Boot 3.5.14
- Maven
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Jakarta Validation
- DTO Pattern (API Response Models)
- Swagger / OpenAPI
- JUnit 5
- Mockito

---

## Backend Package Structure

```text
src/main/java/com/banking
├── account
│   ├── Account
│   ├── AccountController
│   ├── AccountMapper
│   ├── AccountRepository
│   ├── AccountResponse
│   └── AccountService
├── customer
│   ├── Customer
│   ├── CustomerController
│   ├── CustomerMapper
│   ├── CustomerRepository
│   ├── CustomerResponse
│   └── CustomerService
├── transaction
│   ├── Transaction
│   ├── TransactionController
│   ├── TransactionMapper
│   ├── TransactionRepository
│   ├── TransactionResponse
│   ├── TransactionService
│   └── TransactionType
└── common
    └── exception
        ├── BankingException
        ├── GlobalExceptionHandler
        └── ResourceNotFoundException
```

---

## Features

### Customer Management

- Create customers
- View all customers

### Account Management

- Create accounts for customers
- Automatically generate IBAN
- View all accounts

### Money Operations

- Deposit money
- Withdraw money
- Transfer money between accounts

### Transaction History

- View all transactions of an account

### API Design

- DTO-based API responses
- Clean separation between entities and external responses
- Stable and controlled JSON output

### Validation

- Input validation using Jakarta Validation
- Ensures valid request data (e.g. email format, positive amounts)
- Returns clear validation error messages

### Testing

- Unit tests for service layer
- Tested business logic (deposit, withdrawal, transfer)
- Includes edge case testing (e.g. insufficient balance)
- Uses JUnit and Mockito

### Error Handling

- Global exception handler
- Clean JSON error responses
- Proper HTTP status codes (400 / 404)

---

## Domain Model

### Customer

Represents a bank customer.

Fields:

- id
- firstName
- lastName
- email

### Account

Represents a bank account.

Fields:

- id
- iban
- balance
- customer

### Transaction

Represents a money movement.

Fields:

- id
- amount
- type
- direction
- relatedAccountId
- description
- timestamp
- account

---

## API Endpoints

### Customers

Create customer:

```http
POST /api/customers
```

Get all customers:

```http
GET /api/customers
```

---

### Accounts

Create account for customer:

```http
POST /api/accounts/customer/{customerId}
```

Get all accounts:

```http
GET /api/accounts
```

Deposit money:

```http
POST /api/accounts/{accountId}/deposit?amount=500
```

Withdraw money:

```http
POST /api/accounts/{accountId}/withdraw?amount=200
```

Transfer money:

```http
POST /api/accounts/{sourceAccountId}/transfer/{targetAccountId}?amount=100
```

---

### Transactions

Get all transactions for account:

```http
GET /api/transactions/account/{accountId}
```

---

## API Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON is available at:

```text
http://localhost:8080/v3/api-docs
```

---

## Error Response Example

```json
{
  "timestamp": "2026-04-27T16:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Account not found"
}
```

---

## Example Requests

Example requests can be found in:

```text
requests.http
```

---

## How to Run

Start the application in IntelliJ IDEA or run (Windows):

```bash
./mvnw spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

---

## Current Status

Implemented Features:

- Customer API
- Account API
- Deposit
- Withdrawal
- Transfer
- Transaction History
- Global exception handling
- Custom exceptions
- DTO architecture for API responses
- Mapper classes for entity-to-response conversion
- Swagger / OpenAPI documentation
- Unit tests for service layer
- Error case testing

---

## Roadmap

Planned next improvements:

- PostgreSQL support
- Authentication / Security