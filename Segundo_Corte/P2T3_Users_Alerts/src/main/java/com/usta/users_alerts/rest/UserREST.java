package com.usta.users_alerts.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.UserEntity;
import com.usta.users_alerts.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get all users, get a user by id, create a user, update a user, and delete a
 * user
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserREST {
    /**
     * This is a way to inject the `UserService` into the `UserREST` class.
     */
    @Autowired
    private UserService _userService;

    /**
     * Get all users from the database and return them as a list of UserEntity
     * objects
     * 
     * @return A list of users.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(_userService.getAllUsers());
    }

    /**
     * Get a user by id
     * 
     * @param id The id of the user to get.
     * @return The user entity.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_userService.getUserById(id));
    }

    /**
     * `@GetMapping("/total-records")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to
     * the URL "/total-records"
     * 
     * @return The number of records in the user table.
     */
    @GetMapping(value = "/total-records")
    private ResponseEntity<Integer> countTotalUserRecords() {
        return ResponseEntity.ok(_userService.countTotalUserRecords());
    }

    /**
     * Create a new user
     * 
     * @param user The user object that will be created.
     * @return The URI of the newly created resource.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity temp = _userService.createUser(user);
        try {
            return ResponseEntity.created(new URI("/api/users/" + temp.getUserId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Update a user
     * 
     * @param user The user to update.
     * @return The updated user.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
        UserEntity temp = _userService.updateUser(user);
        try {
            return ResponseEntity.created(new URI("/api/users/" + temp.getUserId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Delete a user
     * 
     * @param id The id of the user to delete.
     * @return The deleted user.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<UserEntity> deleteUser(@PathVariable("id") Long id) {
        _userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * It deletes a user from the database.
     * 
     * @param id The id of the user to be deleted.
     * @return A ResponseEntity object.
     */
    @DeleteMapping(value = "/delete-try-catch/{id}")
    private ResponseEntity<String> deleteUserWithTryCatch(@PathVariable("id") Long id) {
        try {
            _userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el usuario");
        }
    }
}
