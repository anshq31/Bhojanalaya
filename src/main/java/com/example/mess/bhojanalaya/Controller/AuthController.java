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
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest request){
//        return ResponseEntity.ok(authenticationService.authenticateUser(request));
//
//
//    }
//    @PostMapping("/logout")
//    public ResponseEntity<String> logOut(HttpServletResponse response){
//        authenticationService.logOut(response);
//        return  ResponseEntity.ok("Logged out successfully");
//    }

}
