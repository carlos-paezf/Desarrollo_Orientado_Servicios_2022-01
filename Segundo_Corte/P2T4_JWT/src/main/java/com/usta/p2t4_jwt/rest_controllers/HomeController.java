package com.usta.p2t4_jwt.rest_controllers;

import com.usta.p2t4_jwt.models.JWTRequest;
import com.usta.p2t4_jwt.models.JWTResponse;
import com.usta.p2t4_jwt.services.UserService;
import com.usta.p2t4_jwt.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility _jwtUtility;

    @Autowired
    private AuthenticationManager _authenticationManager;

    @Autowired
    private UserService _userService;
    
    @GetMapping(value = "/")
    public String home() {
        return "Bienvenido User";
    }

    @PostMapping(value = "/auth/login")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            _authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );
        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrectos", e);
        }

        final UserDetails userDetails = _userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = _jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }
}
