package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.Configuration.JwtService;
import com.example.mess.bhojanalaya.DTO.JwtResponse;
import com.example.mess.bhojanalaya.DTO.AuthenticationRequest;
import com.example.mess.bhojanalaya.DTO.RegisterRequest;
import com.example.mess.bhojanalaya.Enums.Role;
import com.example.mess.bhojanalaya.Model.User;
import com.example.mess.bhojanalaya.Repository.UserRepository;
import com.example.mess.bhojanalaya.Services.Interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public List<User> getStudentsByMess(Long messId) {
        return userRepository.findByMessId(messId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
//
//    @Autowired
//    private final JwtService jwtService;
//    @Autowired
//    private  PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private  AuthenticationManager authenticationManager;
//
//    public JwtResponse registerUser(RegisterRequest request){
//
//        Role role = Role.valueOf(String.valueOf(request.getRole()));
//
//        var user = User.builder()
//                .name(request.getFullName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(role)
//                .build();
//        userRepository.save(user);
//
//
//        var jwtToken = jwtService.generateToken(user);
//        return JwtResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    @Override
//    public JwtResponse authenticateUser(AuthenticationRequest request){
//        authenticationManager.authenticateUser(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        var jwtToken = jwtService.generateToken(user);
//
//        return JwtResponse.builder()
//                .token(jwtToken)
//                .build();

//    }


}
