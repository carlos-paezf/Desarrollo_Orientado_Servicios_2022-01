package com.usta.crud_university.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.University;
import com.usta.crud_university.services.UniversityService;

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
 * The UniversityREST class is a Spring MVC controller that handles HTTP requests for universities
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping("/api/university")
public class UniversityREST {
    /**
     * This is a field injection. The field `_universityService` is injected with
     * the value of `UniversityService`
     */
    @Autowired
    private UniversityService _universityService;

    /**
     * Get all universities from the database
     * 
     * @return A list of universities.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(_universityService.getAllUniversities());
    }

    /**
     * Get a university by id
     * 
     * @param id The id of the university to be retrieved.
     * @return The university object.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<University>> getUniversityById(@PathVariable("id") long id) {
        return ResponseEntity.ok(_universityService.getUniversityById(id));
    }

    /**
     * Create a new university
     * 
     * @param university The university to be created.
     * @return The URI of the newly created resource.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<University> createUniversity(@RequestBody University university) {
        University temp = _universityService.createUniversity(university);
        try {
            return ResponseEntity.created(new URI("/api/university" + temp.getUniversityId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * `@DeleteMapping(value = "/delete/{id}")`
     * 
     * This annotation is used to tell Spring MVC that this function is a handler
     * for HTTP DELETE requests.
     * 
     * The `@PathVariable` annotation is used to tell Spring MVC that the `id`
     * parameter in the path is a variable that needs to be bound to the function.
     * 
     * The `deleteUniversityById` function takes a single parameter, `id`, which is
     * the id of the university to be deleted.
     * 
     * The function returns a `ResponseEntity` object with a status of `NO_CONTENT`.
     * This tells Spring MVC that the function handled the request successfully, but
     * that there is no content to return.
     * 
     * @param id The id of the university to be deleted.
     * @return Nothing.
     */
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Void> deleteUniversityById(@PathVariable("id") long id) {
        try {
            _universityService.deleteUniversityById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).
        }
    }

    /**
     * Update a university by ID
     * 
     * @param university The university object that will be updated.
     * @return The URI of the newly created resource.
     */
    @PutMapping(value = "/update")
    private ResponseEntity<University> updateUniversityWithId(@RequestBody University university) {
        University temp = _universityService.createUniversity(university);
        try {
            return ResponseEntity.created(new URI("/api/university/" + temp.getUniversityId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * `@GetMapping(value = "/total")`
     * 
     * This is a Spring MVC annotation that tells Spring MVC to map this function to the URL "/total"
     * 
     * @return The total number of universities.
     */
    @GetMapping(value = "/total")
    private ResponseEntity<Integer> countTotalUniversityRecords() {
        return ResponseEntity.ok(_universityService.countTotalUniversityRecords());
    }
}
