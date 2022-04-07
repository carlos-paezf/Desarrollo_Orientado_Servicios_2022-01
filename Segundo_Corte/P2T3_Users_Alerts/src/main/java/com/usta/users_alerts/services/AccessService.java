package com.usta.users_alerts.services;

import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.AccessEntity;
import com.usta.users_alerts.repositories.IAccessRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to create, update, and delete access entities
 * 
 * @author Carlos Páez
 */
@Service
public class AccessService {
    /**
     * This is a way to inject the `IAccessRepository` into the `AccessService`
     * class.
     */
    @Autowired
    private IAccessRepository _accessRepository;

    /**
     * Get all access entities from the database
     * 
     * @return A list of AccessEntity objects.
     */
    public List<AccessEntity> getAllAccess() {
        return _accessRepository.findAll();
    }

    /**
     * Get an access entity by its id
     * 
     * @param id The id of the access entity to retrieve.
     * @return Nothing.
     */
    public Optional<AccessEntity> getAccessById(Long id) {
        return _accessRepository.findById(id);
    }

    /**
     * Count the total number of access records in the database
     * 
     * @return The count of total access records.
     */
    public Integer countTotalAccessRecords() {
        return _accessRepository.countTotalAccessRecords();
    }

    /**
     * Create a new access entity
     * 
     * @param access The access entity to be created.
     * @return Nothing.
     */
    public AccessEntity createAccess(AccessEntity access) {
        return _accessRepository.save(access);
    }

    /**
     * Update an access entity
     * 
     * @param access The access entity to be updated.
     * @return Nothing.
     */
    public AccessEntity updateAccess(AccessEntity access) {
        return _accessRepository.save(access);
    }

    /**
     * Delete an access by id
     * 
     * @param id The id of the access to be deleted.
     */
    public void deleteAccess(Long id) {
        _accessRepository.deleteById(id);
    }
}
