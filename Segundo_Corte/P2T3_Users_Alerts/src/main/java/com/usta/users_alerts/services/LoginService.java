package com.usta.users_alerts.services;

import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.LoginEntity;
import com.usta.users_alerts.repositories.ILoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to create, update, and delete logins
 * 
 * @author Carlos Páez
 */
@Service
public class LoginService {
    /**
     * This is a way to inject the `ILoginRepository` interface into the
     * `LoginService` class.
     */
    @Autowired
    private ILoginRepository _loginRepository;

    /**
     * Get all logins from the database
     * 
     * @return A list of LoginEntity objects.
     */
    public List<LoginEntity> getAllLogin() {
        return _loginRepository.findAll();
    }

    /**
     * Get a LoginEntity by id
     * 
     * @param id The id of the login entity to be retrieved.
     * @return A Optional<LoginEntity> object.
     */
    public Optional<LoginEntity> getLoginById(Long id) {
        return _loginRepository.findById(id);
    }

    /**
     * Count the total number of login records in the database
     * 
     * @return The total number of login records.
     */
    public Integer countTotalLoginRecords() {
        return _loginRepository.countTotalLoginRecords();
    }

    /**
     * Create a new login entity and save it to the database
     * 
     * @param login The login entity to be saved.
     * @return The saved login entity.
     */
    public LoginEntity createLogin(LoginEntity login) {
        return _loginRepository.save(login);
    }

    /**
     * Update the login entity with the given id
     * 
     * @param login The login entity to be updated.
     * @return The updated login entity.
     */
    public LoginEntity updateLogin(LoginEntity login) {
        return _loginRepository.save(login);
    }

    /**
     * Delete a login by id
     * 
     * @param id The id of the login to be deleted.
     */
    public void deleteLogin(Long id) {
        _loginRepository.deleteById(id);
    }
}
