package com.usta.crudspring.rest;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.usta.crudspring.models.Professor;
import com.usta.crudspring.services.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/professors/")
public class ProfessorRest {

    /**
     * This is a way to inject the service into the controller.
     */
    @Autowired
    private ProfessorService _professorService;

    /**
     * Get all professors from the database
     *
     * @return A list of professors.
     */
    @GetMapping(value = "/get-all-professors")
    private ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok(_professorService.getAllProfessors());
    }


    /**
     * `@GetMapping(value="/get-professor-by-id")`
     * <p>
     * This is a Spring MVC annotation that tells Spring MVC to map this function to the URL
     * `/get-professor-by-id`
     *
     * @param id The id of the professor to be retrieved.
     * @return A professor object.
     */
    @GetMapping(value = "/get-professor-by-id/{id}")
    private ResponseEntity<Optional<Professor>> getProfessorById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_professorService.getProfessorById(id));
    }

    /**
     * Create a new professor
     *
     * @param professor The professor object that will be created.
     * @return The URI of the newly created professor.
     */
    @PostMapping(value = "/create-professor")
    private ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        Professor temp = _professorService.createProfessor(professor);
        try {
            return ResponseEntity.created(new URI("/api/professors" + temp.getProfessorId())).body(temp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}