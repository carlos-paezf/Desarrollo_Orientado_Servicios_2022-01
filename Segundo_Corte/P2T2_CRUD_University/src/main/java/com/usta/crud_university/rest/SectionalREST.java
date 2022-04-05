package com.usta.crud_university.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Sectional;
import com.usta.crud_university.services.SectionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get all sectional, get a sectional by id, and create a new sectional
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping("/api/sectional")
public class SectionalREST {
    /**
     * This is a constructor injection. The `@Autowired` annotation is used to
     * inject the SectionalService into the SectionalREST class.
     */
    @Autowired
    private SectionalService _sectionalService;

    /**
     * Get all the Sectional
     * 
     * @return A list of Sectional objects.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<Sectional>> getAllSectional() {
        return ResponseEntity.ok(_sectionalService.getAllSectional());
    }

    /**
     * Get a Sectional by its ID
     * 
     * @param id The id of the Sectional to be retrieved.
     * @return The Sectional object.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<Sectional>> getSectionalById(@PathVariable("id") long id) {
        return ResponseEntity.ok(_sectionalService.getSectionalById(id));
    }

    /**
     * Create a new Sectional
     * 
     * @param sectional The Sectional object that will be created.
     * @return The newly created Sectional object.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<Sectional> createSectional(@RequestBody Sectional sectional) {
        Sectional temp = _sectionalService.createSectional(sectional);
        try {
            return ResponseEntity.created(new URI("/api/sectional" + temp.getSectionalId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}