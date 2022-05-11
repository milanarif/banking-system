package org.bank.bankingsystem.controller;

import org.bank.bankingsystem.entity.LoanEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable Long id) {
        UserEntity user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> findAllUsers() {
        Iterable<UserEntity> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        UserEntity updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        String deletedUser = "User with id: " + id + " has been deleted";
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @PutMapping("/createLoan/{userId}")
    public ResponseEntity<UserEntity> createLoan(@PathVariable long userId,
                                                 @QueryParam("amount") long amount){
        UserEntity user = userService.createLoan(userId, amount);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/findAllLoans")
    public ResponseEntity<Iterable<LoanEntity>> findAllLoans(){
        Iterable<LoanEntity> loans = userService.findAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
}