# Banking system

## Installation
In order to start all services, make sure to place this repo and the statlogger-repo in the same parent directory. Then type 'docker compose up' in terminal when in this directory.


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
requires 'ROLE_ADMIN' - Returns all users

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
requires 'ROLE_ADMIN', userId & amount(queryParam)

GET 'http://localhost:8080/users/findAllLoans'
requires 'ROLE_ADMIN' - Returns all loans
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

GET 'http://localhost:8080/accounts/transactions'
requires 'ROLE_ADMIN' - Returns all transactions
```

### Statlogger
```java
GET 'http://localhost:8081/logs'

Returns logs of user authentiaction history
Requires ADMIN credentials in Basic auth
```
