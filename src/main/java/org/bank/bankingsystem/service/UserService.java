package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.AccountEntity;
import org.bank.bankingsystem.entity.LoanEntity;
import org.bank.bankingsystem.entity.RoleEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.AccountRepository;
import org.bank.bankingsystem.repository.LoanRepository;
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
    private final LoanRepository loanRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, AccountRepository accountRepository,  LoanRepository loanRepository, BCryptPasswordEncoder passwordEncoder, AccountService accountService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.loanRepository = loanRepository;
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

    public UserEntity updateUser(Long id, UserEntity user) {

        if (user.getName() == null) {
            throw new CustomException.InvalidUserDetails("Name is null");
        } else if (user.getUsername() == null) {
            throw new CustomException.InvalidUserDetails("Username is null");
        } else if (user.getPassword() == null) {
            throw new CustomException.InvalidUserDetails("Password is null");
        }

        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        for (UserEntity u : users) {
            if (u.getUsername().contentEquals(user.getUsername())) {
                throw new CustomException.AlreadyExistsException("Username: " + user.getUsername() + " already Exists.");
            }
        }
        UserEntity userEntity = findUserById(id);
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity user = findUserById(id);
        if (!user.getUsername().equals("admin")) {
            AccountEntity account = user.getAccount();
            List<LoanEntity> loans = account.getLoans();
            if(!loans.isEmpty()) {
                for (LoanEntity loan : loans) {
                    loanRepository.deleteById(loan.getLoanId());
                }
            }
            accountRepository.delete(account);
            userRepository.delete(user);
        } 
        else {
            throw new CustomException.RemoveAdminException("Admin user cannot be deleted.");
        }

    }

    public Iterable<UserEntity> findAllUsers() {
        Iterable<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        if(users.equals(null))
            throw new CustomException.NotFoundException("No Users was found in database.");
        return users;
    }

    public UserEntity createLoan(Long userId, Long amount) {
        //TODO only role.admin can createLoan
        UserEntity user = findUserById(userId);
        AccountEntity account = user.getAccount();

        LoanEntity newLoan = new LoanEntity(amount, 10.0);
        loanRepository.save(newLoan);

        account.addLoan(newLoan);
        account.setFunds(account.getFunds() + amount);
        accountRepository.save(account);
        return user;
    }

    public Iterable<LoanEntity> findAllLoans() {
        Iterable<LoanEntity> loans = loanRepository.findAll();
        if (loans.equals(null))
            throw new CustomException.NotFoundException("No loans were found.");
        return loans;
    }

    public UserEntity addRole(Long id, String roleName) {
        UserEntity userEntity = findUserById(id);
        userEntity.addRole(roleRepository.findByName(roleName));
        return userRepository.save(userEntity);
    }

    public UserEntity removeRole(Long id, String roleName) {
        UserEntity userEntity = findUserById(id);
        userEntity.removeRole(roleRepository.findByName(roleName));
        return userRepository.save(userEntity);
    }
}
