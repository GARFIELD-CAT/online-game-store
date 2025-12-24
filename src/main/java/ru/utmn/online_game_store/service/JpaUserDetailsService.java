package ru.utmn.online_game_store.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.utmn.online_game_store.repository.UserRepository;

public class JpaUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    PasswordService passwordService;

    public JpaUserDetailsService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final ru.utmn.online_game_store.model.User user = userRepository.findByEmailIgnoreCase(username);

        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .accountLocked(!user.isEnabled())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }
}
