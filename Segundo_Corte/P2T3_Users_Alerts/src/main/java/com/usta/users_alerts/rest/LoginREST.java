package com.usta.users_alerts.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.LoginEntity;
import com.usta.users_alerts.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The LoginREST class is a Spring MVC controller that exposes REST endpoints
 * for the LoginService
 *
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/login")
public class LoginREST {
    // This is a Spring MVC annotation that tells Spring MVC to inject the
    // LoginService object into the LoginREST class.
    @Autowired
    private LoginService _loginService;

    /**
     * Get all the login details from the database
     * 
     * @return A list of LoginEntity objects.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<LoginEntity>> getAllLogin() {
        return ResponseEntity.ok(_loginService.getAllLogin());
    }

    /**
     * Get a Login by ID
     * 
     * @param id The id of the login entity to be retrieved.
     * @return The LoginEntity is being returned.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<LoginEntity>> getLoginById(Long id) {
        return ResponseEntity.ok(_loginService.getLoginById(id));
    }

    /**
     * `@GetMapping(value = "/total-records")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to
     * the URL "/total-records"
     * 
     * @return The total number of login records.
     */
    @GetMapping(value = "/total-records")
    private ResponseEntity<Integer> countTotalLoginRecords() {
        return ResponseEntity.ok(_loginService.countTotalLoginRecords());
    }

    /**
     * Create a new login
     * 
     * @param login The LoginEntity object that will be created.
     * @return The URI of the newly created resource.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<LoginEntity> createLogin(LoginEntity login) {
        LoginEntity temp = _loginService.createLogin(login);
        try {
            return ResponseEntity.created(new URI("/api/login/" + temp.getLoginId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update a login
     * 
     * @param login The LoginEntity object that will be updated.
     * @return The updated login entity.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<LoginEntity> updateLogin(LoginEntity login) {
        LoginEntity temp = _loginService.updateLogin(login);
        try {
            return ResponseEntity.created(new URI("/api/login/" + temp.getLoginId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a login by id
     * 
     * @param id The id of the login to be deleted.
     * @return The HTTP status code and the message.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<String> deleteLogin(Long id) {
        _loginService.deleteLogin(id);
        return ResponseEntity.ok("Login deleted");
    }
}
