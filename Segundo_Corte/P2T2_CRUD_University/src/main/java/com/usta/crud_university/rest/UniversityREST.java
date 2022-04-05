package com.usta.crud_university.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.University;
import com.usta.crud_university.services.UniversityService;

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
 * Get all universities, get a university by id, and create a new university
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
}
