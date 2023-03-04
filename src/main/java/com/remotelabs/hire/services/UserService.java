package com.remotelabs.hire.services;

import com.remotelabs.hire.configs.security.JwtService;
import com.remotelabs.hire.dtos.requests.LoginRequest;
import com.remotelabs.hire.dtos.requests.AddUserDto;
import com.remotelabs.hire.entities.User;
import com.remotelabs.hire.enums.UserRole;
import com.remotelabs.hire.exceptions.HireAuthException;
import com.remotelabs.hire.exceptions.HireInternalException;
import com.remotelabs.hire.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public User createUser(AddUserDto addUserDto, UserRole userRole) {

        validateUserCreation(addUserDto);

        User user = new User();
        user.setUserRole(userRole);
        user.setEmail(addUserDto.getUsername());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(addUserDto.getPassword()));

        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        User user = userRepository
                .findByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new HireAuthException("Login failed User not found."));

        return jwtService.generateToken(user);
    }

    private void validateUserCreation(AddUserDto addUserDto) {
        if (userRepository.findByEmail(addUserDto.getUsername()).isPresent()) {
            throw new HireInternalException(String.format("Email %s is already taken", addUserDto.getUsername()));
        }
    }
}
