package com.usta.users_alerts.services;

import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.RoleEntity;
import com.usta.users_alerts.repositories.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is used to manage the role entity
 * 
 * @author Carlos Páez
 */
@Service
public class RoleService {
    /**
     * This is a field injection. The field `_roleRepository` is injected with the `IRoleRepository` interface.
     */
    @Autowired
    private IRoleRepository _roleRepository;

    /**
     * Get all the roles from the database
     * 
     * @return A list of RoleEntity objects.
     */
    public List<RoleEntity> getAllRoles() {
        return _roleRepository.findAll();
    }

    /**
     * Get a role by its id
     * 
     * @param id The id of the role we want to retrieve.
     * @return The Role object matches the id.
     */
    public Optional<RoleEntity> getRoleById(Long id) {
        return _roleRepository.findById(id);
    }

    /**
     * Count the total number of role records in the database
     * 
     * @return The number of records in the role table.
     */
    public Integer countTotalRoleRecords() {
        return _roleRepository.countTotalRolesRecords();
    }

    /**
     * Create a new role
     * 
     * @param role The role entity to be saved.
     * @return The RoleEntity object that was saved.
     */
    public RoleEntity createRole(RoleEntity role) {
        return _roleRepository.save(role);
    }

    /**
     * Update a role in the database
     * 
     * @param role The role entity to be updated.
     * @return Nothing.
     */
    public RoleEntity updateRole(RoleEntity role) {
        return _roleRepository.save(role);
    }

    /**
     * Delete a role by ID
     * 
     * @param id The id of the role to delete.
     */
    public void deleteRole(Long id) {
        _roleRepository.deleteById(id);
    }
}
