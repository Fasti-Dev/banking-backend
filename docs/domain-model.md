# Domain Model

## Customer

Repräsentiert einen Bankkunden.

Attribute:

- id
- firstName
- lastName
- email

## Account

Repräsentiert ein Bankkonto.

Attribute:

- id
- iban
- balance
- accountType
- customerId

## Transaction

Repräsentiert eine Kontobewegung.

Attribute:

- id
- amount
- type
- timestamp
- sourceAccountId
- targetAccountId

# Beziehungen

- Ein Customer kann mehrere Accounts besitzen.
- Ein Account gehört genau einem Customer.
- Ein Account kann viele Transactions haben.
- Eine Transaction kann eine Einzahlung, Auszahlung oder Überweisung sein.