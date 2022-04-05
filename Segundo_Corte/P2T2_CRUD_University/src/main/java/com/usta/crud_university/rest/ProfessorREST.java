package com.usta.crud_university.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crud_university.models.Professor;
import com.usta.crud_university.services.ProfessorService;

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
 * Get all professors, get a professor by id, and create a new professor
 * 
 * @author Carlos Páez
 */
@RestController
@RequestMapping(value = "/api/professor")
public class ProfessorREST {
    /**
     * This is a Spring annotation that tells Spring to inject the ProfessorService
     * object into the ProfessorREST class.
     */
    @Autowired
    private ProfessorService _professorService;

    /**
     * Get all professors from the database
     * 
     * @return A list of professors.
     */
    @GetMapping(value = "")
    private ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok(_professorService.getAllProfessors());
    }

    /**
     * Get a professor by id
     * 
     * @param id The id of the professor we want to retrieve.
     * @return The professor object.
     */
    @GetMapping(value = "/{id}")
    private ResponseEntity<Optional<Professor>> getProfessorById(@PathVariable("id") long id) {
        return ResponseEntity.ok(_professorService.getProfessorById(id));
    }

    /**
     * Create a new professor
     * 
     * @param professor The professor object that will be created.
     * @return The URI of the newly created professor.
     */
    @PostMapping(value = "/create")
    private ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor temp = _professorService.createProfessor(professor);
        try {
            return ResponseEntity.created(new URI("/api/professor" + temp.getProfessorId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
