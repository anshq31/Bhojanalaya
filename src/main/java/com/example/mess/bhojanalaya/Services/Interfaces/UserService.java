package com.example.mess.bhojanalaya.Services.Interfaces;

import com.example.mess.bhojanalaya.Enums.Role;
import com.example.mess.bhojanalaya.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> getStudentsByMess(Long messId);
    User saveUser(User user);
    Optional<User> findById(Long id);
//    JwtResponse registerUser(RegisterRequest registerRequest);
//    JwtResponse authenticateUser(AuthenticationRequest authenticationRequest);
}
