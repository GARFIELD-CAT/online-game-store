package ru.utmn.online_game_store.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.User;
import ru.utmn.online_game_store.repository.UserRepository;

import java.util.Optional;

@Service
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

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public User getOne(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            return user.get();
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Пользователь с id=%s не существует", id)
            );
        }
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> user = getByEmail(email);

        return user.get();
    }
}
