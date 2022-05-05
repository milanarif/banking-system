package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.RoleEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.bank.bankingsystem.repository.RoleRepository;
import org.bank.bankingsystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException.NotFoundException("User with id: " + id + " could not be found in database."));
    }

    public void createAccount(UserEntity user, AccountEntity account) {
        account.setUser(user);
        accountRepository.save(account);
    }

    public UserEntity createUser(UserEntity user) {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        for (UserEntity u : users) {
            if (u.getUsername().contentEquals(user.getUsername())) {
                throw new CustomException.AlreadyExistsException("Username: " + user.getUsername() + " already Exists.");
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByName("ROLE_USER");
        //TODO
        System.out.println(roleToAdd.toString());
        user.addRole(roleToAdd);

        AccountEntity account = new AccountEntity(1000L);
        createAccount(user, account);

        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity userEntity = findUserById(user.getId());
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userEntity.getUsername() == user.getUsername())
            throw new CustomException.AlreadyExistsException("Username: " + user.getUsername() + " already Exists.");
        
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long socialSecurity) {
UserEntity user = findUserById(socialSecurity);
        userRepository.delete(user);
    }

    public Iterable<UserEntity> findAllUsers() {
        Iterable<UserEntity> users = (List<UserEntity>) userRepository.findAll();
      if(users.equals(null))
          throw new CustomException.NotFoundException("No Users was found in database.");
      return users;
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
