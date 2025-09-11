package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.Configuration.JwtService;
import com.example.mess.bhojanalaya.DTO.SecurityDto.RegisterRequest;
import com.example.mess.bhojanalaya.Enums.Role;
import com.example.mess.bhojanalaya.Model.User;
import com.example.mess.bhojanalaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

//    public JwtResponse registerUser(RegisterRequest request){
//
//        var user = User.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole() != null? request.getRole() : Role.STUDENT)
//                .build();
//        userRepository.save(user);
//
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("name", user.getName());
//        extraClaims.put("email", user.getEmail());
//        extraClaims.put("role", user.getRole().name());
//
//
//        var jwtToken = jwtService.generateToken(extraClaims, user);
//
//
//        return JwtResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
    public ResponseEntity<?> registerUser(RegisterRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );

        // Get role from request (string)
        String requestedRole = String.valueOf(signUpRequest.getRole()); // expect String like "admin" or null
        Role userRole;

        if (requestedRole == null || requestedRole.isBlank()) {
            userRole = Role.STUDENT;  // default role
        } else if (requestedRole.equalsIgnoreCase("admin")) {
            userRole = Role.ADMIN;
        } else {
            userRole = Role.STUDENT;
        }

        user.setRole(userRole);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }


//    public JwtResponse authenticateUser(AuthenticationRequest request, HttpServletResponse response){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("name", user.getName());
//        extraClaims.put("email", user.getEmail());
//        extraClaims.put("role", user.getRole().name());
//
//        var jwtToken = jwtService.generateJwtCookie(user);
//
//
//
//        return JwtResponse.builder()
//                .token(jwtToken)
//                .build();
//
//    }

//    private void addJwtToCookie(String jwtToken , HttpServletResponse response){
//        Cookie cookie = new Cookie("jwt", jwtToken);
//
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//        cookie.setPath("/api");
//        cookie.setMaxAge(1000*60*60*24);
//        response.addCookie(cookie);
//    }
//
//    public void logOut(HttpServletResponse response){
//        Cookie cookie = new Cookie("jwt",null);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//        cookie.setPath("/api");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }

}
