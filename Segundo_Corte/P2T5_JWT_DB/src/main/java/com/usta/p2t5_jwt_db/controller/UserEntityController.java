package com.usta.p2t5_jwt_db.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.p2t5_jwt_db.entities.UserEntity;
import com.usta.p2t5_jwt_db.services.UserEntityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "api/users")
public class UserEntityController {
    private UserEntityService _userEntityService;

    /**
     * This function returns a list of all users in the database
     * 
     * @return A list of UserEntity objects.
     */
    @GetMapping(value = "")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(_userEntityService.getAllUsers());
    }

    /**
     * This function returns a user entity object with the given id
     * 
     * @param id The id of the user you want to get.
     * @return A ResponseEntity object with an Optional object containing a
     *         UserEntity object.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_userEntityService.getUserById(id));
    }

    /**
     * It creates a user and returns the user's information
     * 
     * @param user UserEntity
     * @return A ResponseEntity object.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<UserEntity> createUser(UserEntity user) {
        UserEntity temp = _userEntityService.createUser(user);
        try {
            return ResponseEntity.created(new URI("api/clients/" + temp.getUserId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * It takes a user object, updates the user in the database, and returns the
     * updated user object
     * 
     * @param user UserEntity
     * @return A ResponseEntity object.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<UserEntity> updateClient(UserEntity user) {
        UserEntity temp = _userEntityService.updateUser(user);
        try {
            return ResponseEntity.created(new URI("api/clients/" + temp.getUserId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * It deletes a user from the database
     * 
     * @param id The id of the user to be deleted.
     * @return A ResponseEntity object.
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UserEntity> deleteClient(@PathVariable("id") Long id) {
        _userEntityService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
