package com.usta.crud_university.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Faculty;
import com.usta.crud_university.services.FacultyService;

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
 * Get all faculties, get a faculty by id, and create a new faculty
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping("/api/faculty")
public class FacultyREST {
    /**
     * This is a constructor injection.
     */
    @Autowired
    private FacultyService _facultyService;

    /**
     * Get all faculties
     * 
     * @return A list of Faculty objects.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(_facultyService.getAllFaculties());
    }

    /**
     * Get a faculty by id
     * 
     * @param id The id of the faculty to be retrieved.
     * @return The faculty object.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<Faculty>> getFacultyById(@PathVariable("id") long id) {
        return ResponseEntity.ok(_facultyService.getFacultyById(id));
    }

    /**
     * Create a new faculty
     * 
     * @param faculty The Faculty object that will be created.
     * @return The faculty object that was created.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty temp = _facultyService.createFaculty(faculty);
        try {
            return ResponseEntity.created(new URI("/api/faculty" + temp.getFacultyId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
