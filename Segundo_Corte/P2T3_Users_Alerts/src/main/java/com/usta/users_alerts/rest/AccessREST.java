package com.usta.users_alerts.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.users_alerts.models.AccessEntity;
import com.usta.users_alerts.services.AccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to create, update, and delete access entities
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/access")
public class AccessREST {
    /**
     * This is a Spring MVC annotation that tells Spring MVC to inject the
     * `AccessService` object into this class.
     */
    @Autowired
    private AccessService _accessService;

    /**
     * Get all accesses
     * 
     * @return A list of access entities.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<AccessEntity>> getAllAccess() {
        return ResponseEntity.ok(_accessService.getAllAccess());
    }

    /**
     * Get an access entity by id
     * 
     * @param id The id of the access entity to be retrieved.
     * @return The access entity.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<AccessEntity>> getAccessById(Long id) {
        return ResponseEntity.ok(_accessService.getAccessById(id));
    }

    /**
     * `@GetMapping(value = "/total-records")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to
     * the URL "/total-records"
     * 
     * @return The total number of access records.
     */
    @GetMapping(value = "/total-records")
    private ResponseEntity<Integer> countTotalAccessRecords() {
        return ResponseEntity.ok(_accessService.countTotalAccessRecords());
    }

    /**
     * Create a new access entity
     * 
     * @param accessEntity The access entity that will be created.
     * @return The URI of the newly created resource.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<AccessEntity> createAccess(AccessEntity accessEntity) {
        AccessEntity temp = _accessService.createAccess(accessEntity);
        try {
            return ResponseEntity.created(new URI("/api/access/" + temp.getUserId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing access entity
     * 
     * @param accessEntity The entity that will be updated.
     * @return The updated access entity.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<AccessEntity> updateAccess(AccessEntity accessEntity) {
        AccessEntity temp = _accessService.updateAccess(accessEntity);
        try {
            return ResponseEntity.created(new URI("/api/access/" + temp.getUserId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * `@DeleteMapping(value = "/delete/{id}")`
     * 
     * This annotation is used to tell Spring MVC that this method is a delete
     * method.
     * 
     * The `value` parameter is the URL that will trigger this method.
     * 
     * The `id` parameter is the parameter that will be passed to the method.
     * 
     * The `deleteAccess` method will delete the access with the given id.
     * 
     * @param id The id of the access to be deleted.
     * @return The HTTP status code and the resource.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Void> deleteAccess(Long id) {
        _accessService.deleteAccess(id);
        return ResponseEntity.ok().build();
    }
}
