# Banking-Backend

A Small banking backend built with Java, Spring Boot, Maven and H2 Database.

## About

This project is a learning and portfolio project that demonstrates a simple backend architecture

The application currently supports:

- creating customers
- creating accounts for customers
- depositing money
- withdrawing money
- viewing account balances
- viewing transaction history

## Tech Stack

- Java 21
- Spring Boot 3.5.14
- Maven
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Validation

## Project Structure

```text
src/main/java/com/banking
├── account
├── customer
├── transaction
└── common
```

# Domain Model

## Customer

Represents a bank customer.

Fields:
- id
- firstName
- lastName
- email

## Account

Represents a bank account.

Fields:
- id
- iban
- balance
- customer

## Transaction

Represents a money movement on an account.

Fields:
- id
- amount
- type
- timestamp
- account

# API Endpoints

## Customers

Create Customer:

```http
POST /api/customers
``` 

Get all customers:

```http
GET /api/customers
``` 

## Accounts

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

## Transactions

Get transactions for account:

```http
GET /api/transactions/account/{accountId}
``` 

## Example Requests

Example requests are available in:

```http
requests.http
``` 

# How to Run

Start the application with IntelliJ IDEA or run:

```bash
./mvnw spring-boot:run
```

The application runs on:

```http
http://localhost:8080
``` 

# Current Status

Implemented:
- customer API
- account API
- deposit
- withdrawal
- transaction history

Planned:
- transfer between accounts
- better exception handling
- DTOs
- unit tests
- persistent database configuration