package com.usta.users_alerts.services;

import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.UserEntity;
import com.usta.users_alerts.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The UserService class is a Spring Data JPA implementation of the IUserService
 * interface
 *
 * @author Carlos Páez
 */
@Service
public class UserService {
    /**
     * This is a field injection. The field `_userRepository` is injected with the
     * `IUserRepository` interface.
     */
    @Autowired
    private IUserRepository _userRepository;

    /**
     * Get all users from the database
     * 
     * @return A list of UserEntity objects.
     */
    public List<UserEntity> getAllUsers() {
        return _userRepository.findAll();
    }

    /**
     * Get a user by id
     * 
     * @param id The id of the user to retrieve.
     * @return The User object matches the id.
     */
    public Optional<UserEntity> getUserById(Long id) {
        return _userRepository.findById(id);
    }

    /**
     * Count the total number of user records in the database
     * 
     * @return The countTotalUserRecords method returns an integer.
     */
    public Integer countTotalUserRecords() {
        return _userRepository.countTotalUserRecords();
    }

    /**
     * Create a new user
     * 
     * @param user The user to be created.
     * @return Nothing.
     */
    public UserEntity createUser(UserEntity user) {
        return _userRepository.save(user);
    }

    /**
     * Update the user with the given id
     * 
     * @param user The user to be updated.
     * @return Nothing.
     */
    public UserEntity updateUser(UserEntity user) {
        return _userRepository.save(user);
    }

    /**
     * Delete a user from the database
     * 
     * @param id The id of the user to delete.
     */
    public void deleteUser(Long id) {
        _userRepository.deleteById(id);
    }
}
