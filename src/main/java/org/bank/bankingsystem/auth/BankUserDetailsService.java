package org.bank.bankingsystem.auth;

import org.bank.bankingsystem.entity.UserEntity;
import org.bank.bankingsystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BankUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public BankUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO: Change to findBySocialSecurity
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserEntity userEntity = userRepository.findById();
        UserEntity userEntity = null;
        
        if (userEntity == null) {
            throw new UsernameNotFoundException("Can't find user with username: " + username);
        }

        return new BankPrincipal(userEntity);

    }
}
