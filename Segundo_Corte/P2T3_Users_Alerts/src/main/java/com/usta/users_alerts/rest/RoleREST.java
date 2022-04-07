package com.usta.users_alerts.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.RoleEntity;
import com.usta.users_alerts.services.RoleService;

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
 * Get all roles, get a role by id, create a role, update a role, and delete a role
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/roles")
public class RoleREST {
    /**
     * This is a way to inject the RoleService into the RoleREST class.
     */
    @Autowired
    private RoleService _roleService;

    /**
     * Get all roles from the database and return them as a list of RoleEntity objects
     * 
     * @return A list of RoleEntity objects.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<RoleEntity>> getAllRoles() {
        return ResponseEntity.ok(_roleService.getAllRoles());
    }

    /**
     * Get a role by id
     * 
     * @param id The id of the role to be retrieved.
     * @return The role entity.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<RoleEntity>> getRoleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_roleService.getRoleById(id));
    }

    /**
     * `@GetMapping("/total-records")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to the URL
     * "/total-records"
     * 
     * @return The number of records in the Role table.
     */
    @GetMapping(value = "/total-records")
    private ResponseEntity<Integer> countTotalRoleRecords() {
        return ResponseEntity.ok(_roleService.countTotalRoleRecords());
    }

    /**
     * Create a new role
     * 
     * @param role The role entity that we want to create.
     * @return The URI of the newly created role.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role) {
        RoleEntity temp = _roleService.createRole(role);
        try {
            return ResponseEntity.created(new URI("api/role/" + temp.getRoleId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Update a role
     * 
     * @param role The role entity to be updated.
     * @return The updated role.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<RoleEntity> updateRole(@RequestBody RoleEntity role) {
        RoleEntity temp = _roleService.updateRole(role);
        try {
            return ResponseEntity.created(new URI("api/role/" + temp.getRoleId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * `@DeleteMapping("/delete/{id}")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to the URL
     * `/delete/{id}`. 
     * 
     * The `@PathVariable("id")` annotation is a Spring MVC annotation that tells Spring MVC to pass
     * the value of the `id` parameter in the URL to the `id` parameter in the function. 
     * 
     * The `_roleService.deleteRole(id)` is the actual code that deletes the role. 
     * 
     * The `ResponseEntity.ok().build()` is a Spring MVC annotation that tells Spring MVC to return a
     * `ResponseEntity` with a `200` status code.
     * 
     * @param id The id of the role to be deleted.
     * @return Nothing.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
        _roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
