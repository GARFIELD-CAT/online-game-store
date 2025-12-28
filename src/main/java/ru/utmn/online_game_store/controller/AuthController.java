package ru.utmn.online_game_store.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.utmn.online_game_store.model.dto.LoginRequest;
import ru.utmn.online_game_store.service.PasswordService;
import ru.utmn.online_game_store.service.auth.JwtService;
import ru.utmn.online_game_store.service.auth.TokenBlacklistService;

import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            if (!passwordService.checkPassword(loginRequest.getPassword(), userDetails.getPassword())){
                throw new BadCredentialsException("Введен неправильный пароль");
            };

            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("token", token));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Пользователь с таким email %s не найден.", e.getMessage()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Введен неправильный пароль");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Произошла ошибка: %s", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            tokenBlacklistService.addToken(token);
        }

        return ResponseEntity.ok("Logged out successfully.");
    }
}
