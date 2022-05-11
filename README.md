# Banking system

## Installation

//TODO

## Usage
### User services
```java
Create a user
POST 'http://localhost:8080/users/signup'
requires JSON body
{
    "name": "name",
    "username": "username",
    "password": "password"
}

Authenticate user - returns JWT
POST 'http://localhost:8080/auth'
requires JSON body 
{
    "username": "username",
    "password": "password"
}

// Following requests require TOKEN in header
GET 'http://localhost:8080/users'
Returns all users

GET 'http://localhost:8080/users/{userId}'
Returns user

PUT 'http://localhost:8080/users/{userId}'
Update user
requires JSON body
{
    "name": "newName",
    "username": "newUsername",
    "password": "newPassword"
}

DELETE 'http://localhost:8080/users/{userId}'
Delete user

Create Loan
PUT 'http://localhost:8080/users/createLoan/{userId}?amount=10000'
requires 'ROLE.ADMIN', userId & amount(queryParam)

GET 'http://localhost:8080/users/findAllLoans'
requires 'ROLE.ADMIN' - Returns all loans
```

### Account services
```java
// Following requests require TOKEN in header
GET 'http://localhost:8080/accounts/{accountId}'
Returns user account, user loan and transaction history

Deposit money to user account
PUT 'http://localhost:8080/accounts/deposit/{accountId}/?amount=500'
requires accountId & amount(queryParam)

Withdraw money from user account
PUT 'http://localhost:8080/accounts/withdraw/{accountId}/?amount=500'
requires accountId & amount(queryParam)

Transfer money between accounts
PUT 'http://localhost:8080/accounts/transaction/{senderAccountId}/{receiverAccountId}/?amount=500'
requires Sender accountId, Receiver accountId & amount(queryParam)
'ROLE.USER' will get denied if senderAccountId and token does not match.

GET 'http://localhost:8080/accounts/transactions'
requires 'ROLE.ADMIN' - Returns all transactions
```

### Statlogger
```java
GET 'http://localhost:8081/logs/all'

Returns logs of user authentiaction history
```
