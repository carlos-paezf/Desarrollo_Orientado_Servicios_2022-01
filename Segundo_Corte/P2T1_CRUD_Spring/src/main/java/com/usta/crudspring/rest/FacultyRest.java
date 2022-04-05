package com.usta.crudspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crudspring.models.Faculty;
import com.usta.crudspring.services.FacultyService;

/**
 * Get all faculties, get a faculty by id, and create a new faculty
 */
@RestController
@RequestMapping("/api/faculties")
public class FacultyRest {

    /**
     * This is a injection of FacultyService.
     */
    @Autowired
    private FacultyService _facultyService;

    /**
     * Get all faculties from the database
     *
     * @return A list of Faculty objects.
     */
    @GetMapping("/get-all-faculties")
    private ResponseEntity<List<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(_facultyService.getAllFaculties());
    }

    /**
     * Get a faculty by id
     *
     * @param id The id of the faculty to be retrieved.
     * @return The faculty object.
     */
    @GetMapping("/get-faculty-by-id/{id}")
    private ResponseEntity<Optional<Faculty>> getFacultyById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_facultyService.getFacultyById(id));
    }

    /**
     * Create a new faculty
     *
     * @param faculty The Faculty object that will be created.
     * @return The URI of the newly created faculty.
     */
    @PostMapping(value = "/create-faculty")
    private ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty temp = _facultyService.createFaculty(faculty);
        try {
            return ResponseEntity.created(new URI("/api/faculties" + temp.getFacultyId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
