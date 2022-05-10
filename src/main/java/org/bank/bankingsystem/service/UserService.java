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
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder, AccountService accountService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException.NotFoundException("User with id: " + id + " could not be found in database."));
    }

    public UserEntity createUser(UserEntity user) {

        if(user.getUsername().length() < 3) {
            throw new CustomException.InvalidUserDetails("Username has to be atleast 3 characters");
        } else if (user.getName().length() < 3) {
            throw new CustomException.InvalidUserDetails("Name has to be atleast 3 characters");
        } else if (user.getPassword().length() < 3) {
            throw new CustomException.InvalidUserDetails("Password has to be atleast 3 characters");
        }

        UserEntity initializedUser = initializeUser(user);
        AccountEntity newAccount = createAccount(user);
        initializedUser.setAccount(newAccount);

        return user;
    }

    public UserEntity initializeUser(UserEntity user) {
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        for (UserEntity u : users) {
            if (u.getUsername().contentEquals(user.getUsername())) {
                throw new CustomException.AlreadyExistsException("Username: " + user.getUsername() + " already Exists.");
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByName("ROLE_USER");
        user.addRole(roleToAdd);

        return userRepository.save(user);
    }

    public AccountEntity createAccount(UserEntity user) {
        AccountEntity newAccount = accountService.createAccount(user);
        return newAccount;
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
        AccountEntity account = user.getAccount();
        accountRepository.delete(account);
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
