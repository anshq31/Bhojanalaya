package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.Configuration.JwtService;
import com.example.mess.bhojanalaya.DTO.SecurityDto.AuthenticationRequest;
import com.example.mess.bhojanalaya.DTO.SecurityDto.JwtResponse;
import com.example.mess.bhojanalaya.DTO.SecurityDto.RegisterRequest;
import com.example.mess.bhojanalaya.Enums.Role;
import com.example.mess.bhojanalaya.Model.User;
import com.example.mess.bhojanalaya.Repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public JwtResponse registerUser(RegisterRequest request, HttpServletResponse response){


        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null? request.getRole() : Role.STUDENT)
                .build();
        userRepository.save(user);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("role", user.getRole().name());


        var jwtToken = jwtService.generateToken(extraClaims, user);


        return JwtResponse.builder()
                .token(jwtToken)
                .build();
    }

    public JwtResponse authenticateUser(AuthenticationRequest request, HttpServletResponse response){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("role", user.getRole().name());

        var jwtToken = jwtService.generateJwtCookie(user);



        return JwtResponse.builder()
                .token(jwtToken)
                .build();

    }

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
