package com.example.mess.bhojanalaya.Controller;

import com.example.mess.bhojanalaya.DTO.SecurityDto.AuthenticationRequest;
import com.example.mess.bhojanalaya.DTO.SecurityDto.JwtResponse;
import com.example.mess.bhojanalaya.DTO.SecurityDto.RegisterRequest;
import com.example.mess.bhojanalaya.Repository.UserRepository;
import com.example.mess.bhojanalaya.Services.Implementations.AuthenticationService;
import com.example.mess.bhojanalaya.Services.Implementations.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    private  final  UserServiceImpl userService;

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody RegisterRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(authenticationService.registerUser(request , response));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody AuthenticationRequest request , HttpServletResponse response){
        return ResponseEntity.ok(authenticationService.authenticateUser(request, response));
































































































































































































































































        
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(HttpServletResponse response){
        authenticationService.logOut(response);
        return  ResponseEntity.ok("Logged out successfully");
    }

}
