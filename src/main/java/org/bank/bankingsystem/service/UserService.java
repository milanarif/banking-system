package org.bank.bankingsystem.service;

import org.bank.bankingsystem.entity.RoleEntity;
import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.exception.CustomException;
import org.bank.bankingsystem.repository.RoleRepository;
import org.bank.bankingsystem.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findUserById(Long socialSecurity) {
        return userRepository.findById(socialSecurity).orElseThrow(() -> new CustomException.NotFoundException("User with id: "+ socialSecurity +" could not be found in database."));
    }

    public UserEntity createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleEntity roleToAdd = roleRepository.findByName("ROLE_USER");
        //TODO
        System.out.println(roleToAdd.toString());
        user.addRole(roleToAdd);

        List<UserEntity> users = (List<UserEntity>) findAllUsers();

        for (UserEntity u: users) {
if(u.getUsername() == user.getUsername())
    throw new CustomException.AlreadyExistsException("Username: "+ u.getUsername() +" already Exists.");
        }
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity userEntity = findUserById(user.getId());
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
