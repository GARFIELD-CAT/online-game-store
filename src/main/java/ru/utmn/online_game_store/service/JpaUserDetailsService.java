package ru.utmn.online_game_store.service;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.utmn.online_game_store.model.User;
import ru.utmn.online_game_store.repository.UserRepository;

import java.util.Optional;

public class JpaUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    PasswordService passwordService;

    public JpaUserDetailsService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<ru.utmn.online_game_store.model.User> user = userRepository.findByEmailIgnoreCase(username);

        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.get().getEmail())
                    .accountLocked(!user.get().isEnabled())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole())
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }

    public Optional<User> getByEmail(@NonNull String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}
