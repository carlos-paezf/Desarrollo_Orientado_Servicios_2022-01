package com.usta.p2t5_jwt_db.controller;

import com.usta.p2t5_jwt_db.models.JWTRequest;
import com.usta.p2t5_jwt_db.models.JWTResponse;
import com.usta.p2t5_jwt_db.services.UserService;
import com.usta.p2t5_jwt_db.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carlos Páez
 */
@RestController
public class HomeController {

    @Autowired
    private JWTUtility _jwtUtility;

    @Autowired
    private AuthenticationManager _authenticationManager;

    @Autowired
    private UserService _userService;

    /**
     * The function is called when the user makes a GET request to the root of the
     * application
     * 
     * @return A String
     */
    @GetMapping(value = "/")
    public String home() {
        return "Bienvenido User";
    }

    /**
     * It takes a username and password, checks if they are valid, and if they are,
     * it returns a token
     * 
     * @param jwtRequest is the object that contains the username and password that
     *                   the user enters in the login form.
     * @return A JWTResponse object with a token.
     */
    @PostMapping(value = "/auth/login")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrectos", e);
        }

        final UserDetails userDetails = _userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = _jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }
}
