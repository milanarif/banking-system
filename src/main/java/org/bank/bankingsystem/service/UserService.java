package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.bank.bankingsystem.repository.RoleRepository;
import org.bank.bankingsystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    

    public UserService(UserRepository userRepository, AccountRepository accountRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findUserById(Long socialSecurity) {
        return userRepository.findById(socialSecurity).orElseThrow(() -> new CustomException("User not found"));
    }

    public UserEntity createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccount(accountRepository.save(new AccountEntity()));
        user.addRole(roleRepository.findByName("ROLE_USER"));
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity userEntity = findUserById(user.getSocialSecurity());
        userEntity.setName(user.getName());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long socialSecurity) {
        UserEntity userEntity = findUserById(socialSecurity);
        userRepository.delete(userEntity);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity addRole(Long socialSecurity, String roleName) {
        UserEntity userEntity = findUserById(socialSecurity);
        userEntity.addRole(roleRepository.findByName(roleName));
        return userRepository.save(userEntity);
    }

    public UserEntity removeRole(Long socialSecurity, String roleName) {
        UserEntity userEntity = findUserById(socialSecurity);
        userEntity.removeRole(roleRepository.findByName(roleName));
        return userRepository.save(userEntity);
    }
}